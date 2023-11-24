package rs.payment.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.payment.exchange.dto.requests.LoginRequest;
import rs.payment.exchange.dto.respones.LoginResponse;
import rs.payment.exchange.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {


    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LoginResponse Login (@RequestBody LoginRequest loginRequest){

        LoginResponse response = loginService.login(loginRequest);

        return response;
    }

}
