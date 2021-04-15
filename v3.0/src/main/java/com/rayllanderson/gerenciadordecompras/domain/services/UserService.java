package com.rayllanderson.gerenciadordecompras.domain.services;

import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.mapper.UserMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.repositories.UserRepository;
import com.rayllanderson.gerenciadordecompras.domain.services.utils.StringUtils;
import com.rayllanderson.gerenciadordecompras.domain.services.utils.UpdateData;
import com.rayllanderson.gerenciadordecompras.domain.validations.Assertions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Assertions assertions;
    private final PasswordEncoder encoder;

    @Transactional
    public UserResponseBody save(UserPostRequestBody user) throws IllegalArgumentException {
        assertions.assertThatUsernameNotExists(user.getUsername());
        assertions.assertThatEmailNotExists(user.getEmail());
        User userToBeSaved = UserMapper.toUser(user);
        userToBeSaved.setAuthorities("ROLE_USER");
        userToBeSaved.setPassword(encoder.encode(user.getPassword()));
        return UserMapper.toUserResponseBody(userRepository.save(userToBeSaved));
    }

    @Transactional
    public UserResponseBody saveAnAdmin(UserPostRequestBody user) throws IllegalArgumentException {
        assertions.assertThatUsernameNotExists(user.getUsername());
        assertions.assertThatEmailNotExists(user.getEmail());
        User userToBeSaved = UserMapper.toUser(user);
        userToBeSaved.setAuthorities("ROLE_USER,ROLE_ADMIN");
        userToBeSaved.setPassword(encoder.encode(user.getPassword()));
        return UserMapper.toUserResponseBody(userRepository.save(userToBeSaved));
    }

    @Transactional(readOnly = true)
    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        findById(id);
        userRepository.deleteById(id);
    }

    /**
     * Use this method if you want to update username, email or name
     *
     * Need to set id before send
     *
     * @param user user from the body;
     *
     * @return user updated
     *
     * @throws NotFoundException - if user update username and username has already taken.
     */
    @Transactional
    public UserResponseBody updateNameUsernameOrEmail(UserPutRequestBody user) throws NotFoundException {
        User userFromDataBase = this.findById(user.getId());
        boolean hasUpdateEmail = StringUtils.matches(user.getEmail(), userFromDataBase.getEmail());
        if (hasUpdateEmail) {
            assertions.assertThatEmailNotExists(user.getEmail());
        }
        UpdateData.updateNameUsernameOrEmailFromUser(user, userFromDataBase);
        return UserMapper.toUserResponseBody(this.userRepository.save(userFromDataBase));
    }

    /**
     * Use this method if you want to update ONLY password.
     */
    @Transactional
    public UserResponseBody updatePassword(UserPutRequestBody user) {
        if(passwordIsNotValid(user.getPassword())){
            throw new IllegalArgumentException("Senha está vazia");
        }
        User userFromDataBase = this.findById(user.getId());
        UpdateData.updatePasswordFromUser(user, userFromDataBase);
        userFromDataBase.setPassword(encoder.encode(userFromDataBase.getPassword()));
        return UserMapper.toUserResponseBody(this.userRepository.save(userFromDataBase));
    }

    private boolean passwordIsNotValid(String password){
        return password != null && (password.isEmpty() || password.trim().isEmpty());
    }

}
