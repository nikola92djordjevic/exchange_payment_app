package rs.payment.exchange.service;

import PlatniPrometDB.Tables;
import PlatniPrometDB.tables.daos.UserDao;
import PlatniPrometDB.tables.pojos.Role;
import PlatniPrometDB.tables.pojos.User;
import PlatniPrometDB.tables.pojos.UserAuth;
import PlatniPrometDB.tables.pojos.UserPersonalInfo;
import PlatniPrometDB.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.payment.exchange.dto.requests.UpdatePasswordRequest;
import rs.payment.exchange.dto.requests.UserCreateRequest;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.dto.respones.UserPersonalInfoResponse;
import rs.payment.exchange.dto.security.CryptData;
import rs.payment.exchange.dto.security.HashingUtil;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.exception.error.Error;
import rs.payment.exchange.exception.error.ErrorCode;
import rs.payment.exchange.exception.service.ApiServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static PlatniPrometDB.Sequences.USER_SEQ;
import static PlatniPrometDB.Tables.*;

@Service
public class UserService {

    @Autowired
    private DSLContext dslContext;

    @Autowired
    JWTF<AuthInfo> authJwtf;

    @Autowired
    WorkPlaceService workPlaceService;

    public RpcResponse createUser(UserCreateRequest userCreateRequest) {
        RpcResponse rpcResponse = new RpcResponse();

        if (userCreateRequest.userId == null) {
            userCreateRequest.userId = dslContext.nextval(USER_SEQ);
        }
        User user = userCreateRequest.toUserPojo();
        UserPersonalInfo userPersonalInfo = userCreateRequest.toUserPersonalInfoPojo();
        try {
            dslContext.newRecord(Tables.USER, user).store();
            dslContext.newRecord(Tables.USER_PERSONAL_INFO, userPersonalInfo).store();
            generateCredentials(userCreateRequest.userId, userCreateRequest.password);
            rpcResponse.setOk();
        } catch (Exception e) {
            Error error = new Error(this.getClass().getSimpleName(),
                    ErrorCode.CONFLICT_CODE,
                    new Object() {
                    }.getClass().getEnclosingMethod().getName(),
                    e.getMessage());
            rpcResponse.addError(error);
        }
        return rpcResponse;
    }

    public RpcResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) {

        Long userId = authJwtf.getPayload().getId();

        UserAuth userAuth = dslContext.fetchOne(USER_AUTH, USER_AUTH.USER_ID.eq(userId)).into(UserAuth.class);
        RpcResponse response = new RpcResponse();

        boolean samePassword = HashingUtil.checkPass(updatePasswordRequest.newPassword, userAuth.getSalt(), userAuth.getIteration(), userAuth.getHash());

        if (samePassword) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "New password must be different to the old password"), null);
        }

        boolean correctPass = false;
        correctPass = HashingUtil.checkPass(updatePasswordRequest.oldPassword, userAuth.getSalt(), userAuth.getIteration(), userAuth.getHash());

        if (correctPass) {
            CryptData cryptData = HashingUtil.calculateHash(updatePasswordRequest.newPassword);

            userAuth.setUserId(userId);
            userAuth.setSalt(cryptData.getSalt());
            userAuth.setIteration(cryptData.getIteration());
            userAuth.setHash(cryptData.getHash());
            userAuth.setFlLocked(false);
            userAuth.setLoginCount(0);

            dslContext.newRecord(Tables.USER_AUTH, userAuth).update();
            response.setOk();
        } else {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "Old password does not match!"), null);
        }
        return response;
    }

    public User getUser(Long userId) {
        UserRecord record = dslContext.fetchOne(Tables.USER, Tables.USER.USER_ID.eq(userId));

        if (Objects.isNull(record)) {
            return null;
        }
        return record.into(User.class);
    }

    public User getUSer(Long userId){
        UserDao userDao = new UserDao(dslContext.configuration());
        return userDao.findById(userId);
    }

    public List<User> getAllUsers() {
        UserDao userDao = new UserDao();

        return userDao.fetchByFlActive(true);
    }

    public UserPersonalInfoResponse getUserPersonalInfo(Long userId) {

        Record result = dslContext.select().from(Tables.USER.join(Tables.USER_PERSONAL_INFO).on(Tables.USER.USER_ID.eq(Tables.USER_PERSONAL_INFO.USER_ID)))
                .where(USER.USER_ID.eq(userId)).fetchOne();

        if (result != null) {
            return new UserPersonalInfoResponse(result.into(User.class), result.into(UserPersonalInfo.class));
        } else {
            return null;
        }
    }

    public List<UserPersonalInfoResponse> getAllUsersPersonalInfo() {

        List<UserPersonalInfoResponse> responseList = new ArrayList<>();

        Result<Record> result = dslContext.select().from(Tables.USER.join(Tables.USER_PERSONAL_INFO).on(Tables.USER.USER_ID.eq(Tables.USER_PERSONAL_INFO.USER_ID)))
                .where(USER.FL_ACTIVE.eq(true)).fetch();

        result.forEach((r) -> responseList.add(new UserPersonalInfoResponse(r.into(User.class), r.into(UserPersonalInfo.class))));

        return responseList;
    }

    public UserPersonalInfoResponse editUser(UserCreateRequest userRequest) {

        if (userRequest.userId == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "User id is null"), null);
        }

        UserRecord userRecord = dslContext.fetchOne(USER, USER.USER_ID.eq(userRequest.userId));

        if (userRecord == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            String.format("User with id %s doesn't exist.", userRequest.userId)), null);
        }

        List<String> usernameList = dslContext.select(USER.USERNAME).from(USER).where(USER.USER_ID.notEqual(userRequest.userId).and(USER.FL_ACTIVE.eq(true)))
                .fetch().into(String.class);
        if (usernameList.contains(userRequest.username)) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            String.format("Username  %s already exist.", userRequest.username)), null);
        }
        try {
            dslContext.update(USER).set(USER.USERNAME, userRequest.username)
                    .set(USER.ROLE_ID, userRequest.roleId)
                    .where(USER.USER_ID.eq(userRequest.userId))
                    .execute();

            dslContext.update(USER_PERSONAL_INFO).set(USER_PERSONAL_INFO.FIRST_NAME, userRequest.firstName)
                    .set(USER_PERSONAL_INFO.LAST_NAME, userRequest.lastName)
                    .set(USER_PERSONAL_INFO.DATE_OF_BIRTH, userRequest.dateOfBirth)
                    .set(USER_PERSONAL_INFO.PHONE_NUMBER, userRequest.phoneNumber)
                    .set(USER_PERSONAL_INFO.ADDRESS, userRequest.address)
                    .set(USER_PERSONAL_INFO.CITY, userRequest.city)
                    .where(USER_PERSONAL_INFO.USER_ID.eq(userRequest.userId))
                    .execute();
        } catch (Exception e) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            e.getMessage()), null);
        }

        Record result = dslContext.select().from(Tables.USER.join(Tables.USER_PERSONAL_INFO).on(Tables.USER.USER_ID.eq(Tables.USER_PERSONAL_INFO.USER_ID)))
                .where(USER.USER_ID.eq(userRequest.userId)).fetchOne();

        if (result != null) {
            return new UserPersonalInfoResponse(result.into(User.class), result.into(UserPersonalInfo.class));
        } else {
            return null;
        }
    }

    public RpcResponse deleteUser(Long userId) {
        RpcResponse rpcResponse = new RpcResponse();

        Record workPlace = dslContext.select().from(WORK_PLACE).where(WORK_PLACE.USER_ID.eq(userId).and(WORK_PLACE.TIMESTAMP_OF_CLOSE.isNull())).fetchOne();

        if (workPlace != null) {
            Error error = new Error(this.getClass().getSimpleName(),
                    ErrorCode.CONFLICT_CODE,
                    new Object() {
                    }.getClass().getEnclosingMethod().getName(),
                    "User have open Work place!!!");
            rpcResponse.addError(error);
            return rpcResponse;
        }

        UserRecord userRecord = dslContext.fetchOne(USER, USER.USER_ID.eq(userId));

        if (userRecord == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            String.format("User with id %s doesn't exist.", userId)), null);
        }

        try {
            dslContext.update(USER).set(USER.FL_ACTIVE, false).where((USER.USER_ID.eq(userId))).execute();
            rpcResponse.setOk();
        } catch (Exception e) {
            Error error = new Error(this.getClass().getSimpleName(),
                    ErrorCode.CONFLICT_CODE,
                    new Object() {
                    }.getClass().getEnclosingMethod().getName(),
                    e.getMessage());
            rpcResponse.addError(error);
        }
        return rpcResponse;
    }

    public List<Role> getRoles() {

        return dslContext.select().from(ROLE).fetch().into(Role.class);
    }

    private void generateCredentials(Long userId, String password) {
        CryptData cryptData = HashingUtil.calculateHash(password);

        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(userId);
        userAuth.setSalt(cryptData.getSalt());
        userAuth.setIteration(cryptData.getIteration());
        userAuth.setHash(cryptData.getHash());
        userAuth.setFlLocked(false);
        userAuth.setLoginCount(0);

        dslContext.newRecord(Tables.USER_AUTH, userAuth).store();
    }
}
