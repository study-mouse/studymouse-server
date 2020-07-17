package com.studymouse.studymouseserver.service;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.user.User;
import com.studymouse.studymouseserver.user.UserRepository;
import com.studymouse.studymouseserver.user.dto.UserInfoResDto;
import com.studymouse.studymouseserver.user.dto.UserLoginReqDto;
import com.studymouse.studymouseserver.user.dto.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void save(UserReqDto userReqDto) {
        if (userRepository.findByEmail(userReqDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
        userRepository.save(userReqDto.toEntity());
    }

    @Transactional
    public boolean togglePushMail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }

        User findUser = user.get();

        return findUser.togglePushMail();
    }

    @Transactional
    public UserInfoResDto findByEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);

        if (!findUser.isPresent()) {
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }

        return new UserInfoResDto(findUser.get());
    }

    public AccessUser login(final UserLoginReqDto userLoginReqDto) {
        Optional<User> findUser = userRepository.findByEmail(userLoginReqDto.getEmail());

        if (!findUser.isPresent()) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }

        return new AccessUser(findUser.get());
    }
}