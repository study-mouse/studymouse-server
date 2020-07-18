package com.studymouse.studymouseserver.service;

//import com.studymouse.studymouseserver.security.LoginUser;
import com.studymouse.studymouseserver.security.dto.AccessUser;
import com.studymouse.studymouseserver.security.store.AccessUserManager;
import com.studymouse.studymouseserver.user.User;
import com.studymouse.studymouseserver.user.UserRepository;
import com.studymouse.studymouseserver.user.dto.UserInfoResDto;
import com.studymouse.studymouseserver.user.dto.UserLoginReqDto;
import com.studymouse.studymouseserver.user.dto.UserMailResDto;
import com.studymouse.studymouseserver.user.dto.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public AccessUser login(final UserLoginReqDto userLoginReqDto) {
        Optional<User> findUser = userRepository.findByEmail(userLoginReqDto.getEmail());
        if (!findUser.isPresent()) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
        return new AccessUser(findUser.get());
    }

    public List<UserMailResDto> findAllPushMailsUser() {
        return userRepository.findAllByPushMail(true).stream()
                .map(UserMailResDto::of)
                .collect(Collectors.toList());
    }

    public User getNowAccessUser(AccessUser accessUser) {
        return userRepository.findByEmail(accessUser.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

    }
}
