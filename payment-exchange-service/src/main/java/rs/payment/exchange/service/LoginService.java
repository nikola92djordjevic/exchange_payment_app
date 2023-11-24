package rs.payment.exchange.service;

import PlatniPrometDB.Tables;
import PlatniPrometDB.tables.daos.UserAuthDao;
import PlatniPrometDB.tables.pojos.User;
import PlatniPrometDB.tables.pojos.UserAuth;
import PlatniPrometDB.tables.pojos.UserPersonalInfo;
import PlatniPrometDB.tables.records.UserAuthRecord;
import PlatniPrometDB.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rs.payment.exchange.exception.error.Error;
import rs.payment.exchange.exception.error.ErrorCode;
import rs.payment.exchange.exception.service.ApiServiceException;
import rs.payment.exchange.service.LoginService;
import rs.payment.exchange.dto.requests.LoginRequest;
import rs.payment.exchange.dto.respones.LoginResponse;
import rs.payment.exchange.dto.security.HashingUtil;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.JWTFWrap;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.dto.security.auth.AuthJwtfEncoder;

import java.security.Key;

import static PlatniPrometDB.Tables.*;

@Service
public class LoginService {

    private static final Key hmac256SigningKey = HashingUtil.hmacSha256FromBase64("5lvxDJzEkR57kKDFMlxwtafT52sOZhO6qIljBqsXf/w=");
    @Autowired
    DSLContext db;

    public LoginResponse login(LoginRequest loginRequest) {

        UserRecord userRecord = db.fetchOne(USER, USER.USERNAME.eq(loginRequest.username));

        if (userRecord == null) {

            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.INTERNAL_SERVER_ERROR_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "Wrong username!!!"), null);
        }
        User user = userRecord.into(User.class);
        UserPersonalInfo userPersonalInfo = db.fetchOne(USER_PERSONAL_INFO, USER_PERSONAL_INFO.USER_ID.eq(user.getUserId())).into(UserPersonalInfo.class);

        UserAuthRecord userAuthRecord = db.fetchOne(Tables.USER_AUTH, Tables.USER_AUTH.USER_ID.eq(user.getUserId()));

        if (userAuthRecord == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.INTERNAL_SERVER_ERROR_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "Wrong username!!!"), null);
        }
        UserAuth userAuth = userAuthRecord.into(UserAuth.class);

        boolean passwordCorrect = HashingUtil.checkPass(loginRequest.password, userAuth.getSalt(), userAuth.getIteration(), userAuth.getHash());

        if(passwordCorrect){

            userAuth.setLoginCount(0);

            AuthInfo authInfo = new AuthInfo();
            authInfo.setId(user.getUserId());
            authInfo.setRole(user.getRoleId());
            authInfo.setUsername(user.getUsername());

            JWTF<AuthInfo> jwtf = JWTFWrap.producePlain();
            jwtf.setIss("PLATNI_PROMET");
            jwtf.setSub("auth");
            jwtf.setAud(new String[]{"PLPR"});
            jwtf.setPayload(authInfo);


            StringBuilder jws = new StringBuilder();
            AuthJwtfEncoder encoder = new AuthJwtfEncoder(hmac256SigningKey);
            JWTF.ProcessingState jwtfProcessingState = JWTF.encodeToJwt(jwtf, jws, encoder);

            if (jwtfProcessingState.isSuccess()) {
                LoginResponse response = new LoginResponse();

                String message = "Login successful";
                response.setResponseCode(HttpStatus.OK.value());
                response.setResponseMessage(message);
                response.setToken(jws.toString());
                response.setUserId(user.getUserId());
                response.setUsername(user.getUsername());
                response.setFullName(userPersonalInfo.getFirstName() + ' ' + userPersonalInfo.getLastName());
                response.setRoleId(user.getRoleId());


                userAuth.setLoginCount(0);
                UserAuthDao userAuthDao = new UserAuthDao(db.configuration());
                userAuthDao.update(userAuth);
                return response;
            } else {

                LoginResponse response = new LoginResponse();
                String message = "Error signing the token!!!";
                response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setResponseMessage(message);
                throw new ApiServiceException(
                        new Error(
                                this.getClass().getSimpleName(),
                                ErrorCode.INTERNAL_SERVER_ERROR_CODE,
                                new Object() {
                                }.getClass().getEnclosingMethod().getName(),
                                "Error signing the token"), null);
            }

        }else{
            userAuth.setLoginCount(userAuth.getLoginCount()+1);
            UserAuthDao userAuthDao = new UserAuthDao(db.configuration());
            userAuthDao.update(userAuth);

            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.INTERNAL_SERVER_ERROR_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "Wrong password!!!"), null);
        }

    }
}
