package rs.payment.exchange.dto.requests;

import PlatniPrometDB.tables.pojos.User;
import PlatniPrometDB.tables.pojos.UserPersonalInfo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserCreateRequest {
    public Long userId;
    public String username;

    public String password;
    public Integer roleId;
    public Boolean flActive;
    public LocalDate dateOfStart;
    public LocalDate dateOfEnd;
    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String phoneNumber;
    public String address;
    public String city;
    public LocalDateTime timestampOfCreation;

    public User toUserPojo(){
        User user = new User();
        user.setUserId(this.userId);
        user.setUsername(this.username);
        user.setRoleId(this.roleId);
        user.setFlActive(this.flActive);
        user.setDateOfStart(this.dateOfStart);
        user.setDateOfEnd(this.dateOfEnd);
        user.setTimestampOfCreation(this.timestampOfCreation);
        return user;
    }

    public UserPersonalInfo toUserPersonalInfoPojo(){
        UserPersonalInfo userPersonalInfo = new UserPersonalInfo();
        userPersonalInfo.setUserId(this.userId);
        userPersonalInfo.setFirstName(this.firstName);
        userPersonalInfo.setLastName(this.lastName);
        userPersonalInfo.setDateOfBirth(this.dateOfBirth);
        userPersonalInfo.setPhoneNumber(this.phoneNumber);
        userPersonalInfo.setAddress(this.address);
        userPersonalInfo.setCity(this.city);
        userPersonalInfo.setTimestampOfCreation(timestampOfCreation);
        return userPersonalInfo;
    }
}
