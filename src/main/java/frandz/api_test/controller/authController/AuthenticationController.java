package frandz.api_test.controller.authController;

import frandz.api_test.exception.EmailExistException;
import frandz.api_test.requests.RegisterRequest;
import frandz.api_test.responses.AuthenticationResponse;
import frandz.api_test.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static frandz.api_test.constant.SecurityConstant.APP_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(value ="/api/v1/auth/")
public class AuthenticationController {

    private final AuthService authService;

    //register
    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws EmailExistException {
        return ResponseEntity.ok(this.authService.register(request));
    }


    //login

}
