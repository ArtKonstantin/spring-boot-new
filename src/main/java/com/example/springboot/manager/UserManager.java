package com.example.springboot.manager;

import com.example.springboot.entity.UserEntity;
import com.example.springboot.exception.UserNotFoundException;
import com.example.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.springboot.dto.UserRequestDTO;
import com.example.springboot.dto.UserResponseDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private final NamedParameterJdbcOperations jdbcOperations;
    private final RowMapper<UserResponseDTO> rowMapper = (rs, rowNum) ->
            new UserResponseDTO(rs.getLong("id"), rs.getString("login"));
    private final Function<UserEntity, UserResponseDTO> userEntityToUserResponseDTO = userEntity -> new UserResponseDTO(userEntity.getId(), userEntity.getLogin());

    public List<UserResponseDTO> getAll() {
       return userRepository.findAll().stream()
               .map(userEntityToUserResponseDTO)
               .collect(Collectors.toList());
    }

    public UserResponseDTO getById(final long id) {
        return userRepository.findById(id)
                .map(userEntityToUserResponseDTO)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserResponseDTO create(final UserRequestDTO requestDTO) {
        final UserEntity userEntity = new UserEntity(0, requestDTO.getLogin(), passwordEncoder.encode(requestDTO.getPassword()));
        final var savedEntity = userRepository.save(userEntity);
        return userEntityToUserResponseDTO.apply(savedEntity);
    }

    public UserResponseDTO update(final UserRequestDTO requestDTO) {
        final UserEntity userEntity = userRepository.getReferenceById(requestDTO.getId());
        userEntity.setLogin(requestDTO.getLogin());
        userEntity.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        return userEntityToUserResponseDTO.apply(userEntity);
    }

    public void deleteById(final long id) {
        userRepository.deleteById(id);
    }

    public void authenticate(String login, String password) {
        final var byLogin = userRepository.findByLogin(login);
        if (byLogin == null) {
            throw new UserNotFoundException();
        } else if (equals(passwordEncoder.encode(), password))
    }
}
