package rs.payment.exchange.dto.respones;

import PlatniPrometDB.tables.pojos.User;
import PlatniPrometDB.tables.pojos.UserPersonalInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserPersonalInfoResponse{
    public Long userId;
    public String username;
    public Integer roleId;
    public Boolean flActive;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate dateOfStart;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate dateOfEnd;
    public String firstName;
    public String lastName;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate dateOfBirth;
    public String phoneNumber;
    public String address;
    public String city;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime timestampOfCreation;

    public UserPersonalInfoResponse() {
    }

    public UserPersonalInfoResponse(User user, UserPersonalInfo userPersonalInfo) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.roleId = user.getRoleId();
        this.flActive = user.getFlActive();
        this.dateOfStart = user.getDateOfStart();
        this.dateOfEnd = user.getDateOfEnd();
        this.timestampOfCreation = user.getTimestampOfCreation();
        this.firstName = userPersonalInfo.getFirstName();
        this.lastName = userPersonalInfo.getLastName();
        this.dateOfBirth = userPersonalInfo.getDateOfBirth();
        this.phoneNumber = userPersonalInfo.getPhoneNumber();
        this.address = userPersonalInfo.getAddress();
        this.city = userPersonalInfo.getCity();
    }
}
