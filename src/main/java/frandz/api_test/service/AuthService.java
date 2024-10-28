package frandz.api_test.service;

import frandz.api_test.config.JwtService;
import frandz.api_test.exception.EmailExistException;
import frandz.api_test.exception.ExceptionHandling;
import frandz.api_test.model.User;
import frandz.api_test.model.VerificationToken;
import frandz.api_test.repository.UserRepository;
import frandz.api_test.repository.VerificationTokenRepository;
import frandz.api_test.requests.RegisterRequest;
import frandz.api_test.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class AuthService extends ExceptionHandling {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final JwtService jwtService;


    public AuthenticationResponse register(RegisterRequest request) throws EmailExistException {
        var user = this.userRepository.findByEmail(request.getEmail());
        if(user.isPresent())
            throw new EmailExistException("email is already exist");
        var newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .accountNonExpired(false)
                .enable(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .build();
        this.userRepository.save(newUser);

        //generation de token pour la connexion
        var jwtToken = this.jwtService.generateToken(newUser);
        //generation random code
        String code = this.generateCode();
        //verification token
        VerificationToken verificationToken = new VerificationToken(code,newUser);
        this.verificationTokenRepository.save(verificationToken);

        //after save, send code to user

        return AuthenticationResponse.builder()
                .user(newUser)
                .token(jwtToken)
                .message("email send")
                .build();
    }



    public String generateCode() {
        Random random = new Random();
        Integer code = 100000 + random.nextInt(900000);
        return code.toString();
    }

}
