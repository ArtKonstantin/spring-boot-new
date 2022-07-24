package com.example.springboot.manager;

import com.example.springboot.entity.UserEntity;
import com.example.springboot.exception.*;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.security.Authentication;
import com.example.springboot.security.Roles;
import lombok.RequiredArgsConstructor;
import com.example.springboot.dto.UserRequestDTO;
import com.example.springboot.dto.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Function<UserEntity, UserResponseDTO> userEntityToUserResponseDTO = userEntity -> new UserResponseDTO(userEntity.getId(), userEntity.getLogin(), userEntity.getRoles());

    public List<UserResponseDTO> getAll(final Authentication authentication) {
        if (!authentication.hasRole(Roles.ROLE_ADMIN)) {
            throw new ForbiddenException();
        }

       return userRepository.findAll().stream()
               .map(userEntityToUserResponseDTO)
               .collect(Collectors.toList());
    }

    public UserResponseDTO getById(final Authentication authentication, final long id) {
        if (!authentication.hasRole(Roles.ROLE_ADMIN)) {
            throw new ForbiddenException();
        }

        return userRepository.findById(id)
                .map(userEntityToUserResponseDTO)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserResponseDTO create(final Authentication authentication, final UserRequestDTO requestDTO) {
        if (!authentication.hasRole(Roles.ROLE_ADMIN)) {
            throw new ForbiddenException();
        }

        final UserEntity userEntity = new UserEntity(0, requestDTO.getLogin(), passwordEncoder.encode(requestDTO.getPassword()), requestDTO.getRoles());
        final var savedEntity = userRepository.save(userEntity);
        return userEntityToUserResponseDTO.apply(savedEntity);
    }

    public UserResponseDTO update(final Authentication authentication, final UserRequestDTO requestDTO) {
        if (!authentication.hasRole(Roles.ROLE_ADMIN)) {
            throw new ForbiddenException();
        }

        final UserEntity userEntity = userRepository.getReferenceById(requestDTO.getId());
        userEntity.setLogin(requestDTO.getLogin());
        userEntity.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        userEntity.setRoles(requestDTO.getRoles());
        return userEntityToUserResponseDTO.apply(userEntity);
    }

    public void deleteById(final Authentication authentication, final long id) {
        if (!authentication.hasRole(Roles.ROLE_ADMIN)) {
            throw new ForbiddenException();
        }

        userRepository.deleteById(id);
    }

    public Authentication authenticateByLoginAndPassword(
            final String login,
            final String password
    ) {
        final UserEntity userEntity = userRepository.findByLogin(login).orElseThrow(UserLoginNotFoundException::new);

        if(!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new UserPasswordNotMatchesException();
        }

        final Authentication authentication = Authentication.builder()
                .id(userEntity.getId())
                .roles(new ArrayList<>(userEntity.getRoles()))
                .build();

        return authentication;
    }
}
