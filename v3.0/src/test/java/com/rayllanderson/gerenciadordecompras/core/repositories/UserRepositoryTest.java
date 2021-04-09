package com.rayllanderson.gerenciadordecompras.core.repositories;

import com.rayllanderson.gerenciadordecompras.core.model.User;
import com.rayllanderson.gerenciadordecompras.utils.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for UserRepository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_PersistUser_WhenSuccessful() {
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = userRepository.save(userToBeSaved);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    void save_UpdatesUser_WhenSuccessful() {
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = userRepository.save(userToBeSaved);

        Assertions.assertThat(userSaved).isNotNull();

        userSaved.setName("Fernando");

        User userUpdated = userRepository.save(userSaved);

        Assertions.assertThat(userUpdated).isNotNull();
        Assertions.assertThat(userUpdated.getId()).isNotNull();
        Assertions.assertThat(userUpdated.getName()).isEqualTo(userSaved.getName());
    }

    @Test
    void delete_RemovesUser_WhenSuccessful() {
        User userToBeSaved = UserCreator.createUserToBeSaved();
        User userSaved = userRepository.save(userToBeSaved);

        userRepository.delete(userSaved);

        Optional<User> userOptional = userRepository.findById(userSaved.getId());

        Assertions.assertThat(userOptional).isEmpty();
        Assertions.assertThat(userOptional).isNotPresent();
    }
}