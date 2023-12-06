package admin_user.applicationsrunners;

import admin_user.model.Role;
import admin_user.model.User;
import admin_user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminRunner implements ApplicationRunner {
    private final UserRepository userRepository;

    @Value("${custom.mail}")
    String username;
    @Value("${custom.password}")
    String password;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(
                new User(
                        username, password, Role.ADMIN
                )
        );
    }
}
