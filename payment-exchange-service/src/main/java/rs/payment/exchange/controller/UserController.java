package rs.payment.exchange.controller;

import PlatniPrometDB.tables.pojos.Role;
import PlatniPrometDB.tables.pojos.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.payment.exchange.dto.requests.UpdatePasswordRequest;
import rs.payment.exchange.dto.requests.UserCreateRequest;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.dto.respones.UserPersonalInfoResponse;
import rs.payment.exchange.service.UserService;

import java.util.List;

    @RestController
    @RequestMapping("/api/user")
    public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @RequestMapping(
                method = RequestMethod.POST,
                path = "/create",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<RpcResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {

            RpcResponse response = userService.createUser(userCreateRequest);

            return ResponseEntity.status(response.getResponseCode()).body(response);
        }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserPersonalInfoResponse> editUser(@RequestBody UserCreateRequest userCreateRequest) throws Exception {


        return ResponseEntity.ok(userService.editUser(userCreateRequest));

    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/updatePassword",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) throws Exception {


        return ResponseEntity.ok(userService.updatePassword(updatePasswordRequest));

    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getUser/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUser(@PathVariable Long userId) {

        User response = userService.getUser(userId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getAllUsers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> response = userService.getAllUsers();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getUserPersonalInfo/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserPersonalInfoResponse> getUserPersonalInfo(@PathVariable Long userId) {

        UserPersonalInfoResponse response = userService.getUserPersonalInfo(userId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getAllUsersPersonalInfo",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<UserPersonalInfoResponse>> getAllUsersPersonalInfo() {

        List<UserPersonalInfoResponse> response = userService.getAllUsersPersonalInfo();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/deleteUser/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> deleteUser(@PathVariable Long userId) {

        RpcResponse response = userService.deleteUser(userId);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getRoles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Role>> getRoles() {

        List<Role> response = userService.getRoles();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }
}
