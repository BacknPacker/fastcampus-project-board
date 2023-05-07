package fastcampus.projectboard.config;

import fastcampus.projectboard.domain.UserAccount;
import fastcampus.projectboard.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp(){
        given(userAccountRepository.findById(anyString()))
                .willReturn(Optional.of(UserAccount.of(
                        "queTest",
                        "pw",
                        "que-test@email.com",
                        "que-test",
                        "test memo"
                )));
    }

}
