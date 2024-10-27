package frandz.api_test.service;

import frandz.api_test.exception.EmailExistException;
import frandz.api_test.exception.ExceptionHandling;
import frandz.api_test.model.User;
import frandz.api_test.repository.UserRepository;
import frandz.api_test.requests.RegisterRequest;
import frandz.api_test.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService extends ExceptionHandling {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) throws EmailExistException {
        var user = this.userRepository.findByEmail(request.getEmail());
        if(user.isPresent())
            throw new EmailExistException("email is already exist");
        var newUser = User.builder()
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .build();

        this.userRepository.save(newUser);

        return null;
    }

}
