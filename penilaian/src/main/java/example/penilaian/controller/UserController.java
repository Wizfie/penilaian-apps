package example.penilaian.controller;


import example.penilaian.model.RegisterUserRequest;
import example.penilaian.model.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import example.penilaian.service.UserService;

@RestController
//@CrossOrigin("http://192.168.43.176:5173")
@CrossOrigin("*")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/api/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request){
        userService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }




}