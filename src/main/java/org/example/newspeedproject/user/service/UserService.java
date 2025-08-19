package org.example.newspeedproject.user.service;

import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.user.dto.UserRequestDto;
import org.example.newspeedproject.user.dto.UserResponseDto;
import org.example.newspeedproject.user.entity.User;
import org.example.newspeedproject.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto login(UserRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        // chk password

        return new UserResponseDto(user.getId());
    }

    @Transactional
    public UserResponseDto save(UserRequestDto request) {
        User user = new User(request.getUsername(),request.getEmail(),request.getPassword());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedDateAt(),
                savedUser.getModifiedDateAt()
        );
    }

    @Transactional
    public UserResponseDto findUserId(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedDateAt(),
                user.getModifiedDateAt()
        );
    }

    @Transactional
    public UserResponseDto updateUserProfile(Long id, UserRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );

        // chk password

        user.updateUser(request.getUsername(),request.getEmail());
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedDateAt(),
                user.getModifiedDateAt()
        );
    }

    // add update user password

    @Transactional
    public void deleteUser(Long id, UserRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );

        //chk password

        userRepository.delete(user);
    }
}
