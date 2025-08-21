package org.example.newspeedproject.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.newspeedproject.auth.dto.LoginRequestDto;
import org.example.newspeedproject.config.PasswordEncoder;
import org.example.newspeedproject.user.dto.*;
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
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponseDto findUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }

    @Transactional
    public UserResponseDto updateUserProfile(Long id, ProfileChangeRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));
        user.updateUser(request.getUsername(), request.getEmail());
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }

    @Transactional
    public void passwordChange(Long id, PasswordChangeRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));

        if(!passwordEncoder.matches(request.getOldpassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(passwordEncoder.matches(request.getNewpassword(), user.getPassword())) {
            throw new IllegalArgumentException("변경될 비밀번호가 이전 비밀번호와 동일합니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getNewpassword());
        user.updatePassword(encodedPassword);
    }

    @Transactional
    public void deleteUser(Long id, LoginRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));

        if (!user.getEmail().equals(request.getEmail())) {
            throw new IllegalArgumentException("이메일이 일치하지 않습니다.");
        }

        if(!passwordEncoder.matches(request.getPassword(),  user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);
    }
}
