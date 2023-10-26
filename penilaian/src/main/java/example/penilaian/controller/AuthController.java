package example.penilaian.controller;

import example.penilaian.model.LoginUserRequest;
import example.penilaian.model.WebResponse;
import example.penilaian.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping(
            path = "/api/auth/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> login(@RequestBody LoginUserRequest request){
        authService.login(request);

        return WebResponse.<String>builder().data("Login Success").build();
    }




}
