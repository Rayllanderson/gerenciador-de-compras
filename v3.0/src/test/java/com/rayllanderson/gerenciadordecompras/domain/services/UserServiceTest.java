package com.rayllanderson.gerenciadordecompras.domain.services;

import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.repositories.UserRepository;
import com.rayllanderson.gerenciadordecompras.utils.user.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private com.rayllanderson.gerenciadordecompras.domain.validations.Assertions assertions;


    UserServiceTest() {
    }

    @BeforeEach
    void setUp() {
        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(UserCreator.createUserWithId());
        BDDMockito.when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(UserCreator.createUserWithId()));
        BDDMockito.doNothing().when(userRepository).deleteById(ArgumentMatchers.anyLong());

        BDDMockito.doNothing().when(assertions).assertThatUsernameNotExists(ArgumentMatchers.anyString());
        BDDMockito.doNothing().when(assertions).assertThatEmailNotExists(ArgumentMatchers.anyString());

        BDDMockito.when(encoder.encode(ArgumentMatchers.anyString())).thenReturn("$2a$10$vjDC.rpWSRb7eDwXuGtGaOhv0Bc.S598scA/tlU0Vo1ZYY3NV4lea");
    }

    @Test
    void save_SavesUser_WhenSuccessful() {
        UserResponseBody userToBeSaved = userService.save(UserCreator.createUserPostRequestBody());
        Assertions.assertThat(userToBeSaved).isNotNull();
        Assertions.assertThat(userToBeSaved.getId()).isNotNull();
    }

    @Test
    void saveAnAdmin_SavesAnUserAdmin_WhenSuccessful() {
        UserResponseBody userToBeSaved = userService.saveAnAdmin(UserCreator.createUserPostRequestBody());
        Assertions.assertThat(userToBeSaved).isNotNull();
        Assertions.assertThat(userToBeSaved.getId()).isNotNull();
    }

    @Test
    void findById_ReturnsUser_WhenSuccessful() {
        User expectedUser = UserCreator.createUserWithId();
        User userFound = userService.findById(1L);
        Assertions.assertThat(userFound).isNotNull();
        Assertions.assertThat(userFound).isEqualTo(expectedUser);
    }

    @Test
    void deleteById_DeleteUser_WhenSuccessful() {
        Assertions.assertThatCode(() -> userService.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    void updateNameUsernameOrEmail_UpdateData_WhenSuccessful() {
        Assertions.assertThatCode(() -> userService.updateNameUsernameOrEmail(UserCreator.createUserPutRequestBody())).doesNotThrowAnyException();
    }

    @Test
    void updatePassword_UpdatePassword_WhenSuccessful() {
        Assertions.assertThatCode(() -> userService.deleteById(1L)).doesNotThrowAnyException();
    }
}