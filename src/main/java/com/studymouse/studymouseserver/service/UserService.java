package com.studymouse.studymouseserver.service;

//import com.studymouse.studymouseserver.security.LoginUser;

import com.studymouse.studymouseserver.user.User;
import com.studymouse.studymouseserver.user.UserRepository;
import com.studymouse.studymouseserver.user.dto.UserInfoResDto;
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
//    private final AccessUserManager accessUserManager;

    public static String EMAIL = "shsdf302@gmail.com";

    @Transactional
    public UserInfoResDto login(UserReqDto userReqDto) {
        User user = userRepository.findByEmail(EMAIL)
                .orElseGet(()->userRepository.save(userReqDto.toEntity()));
//        accessUserManager.saveSession(new AccessUser(user));
        return new UserInfoResDto(user);
    }

//    public void logout() {
//        accessUserManager.deleteSession();
//    }

    @Transactional
    public boolean togglePushMail(final String email) {
        Optional<User> user = userRepository.findByEmail(EMAIL);
        if (!user.isPresent()) {
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }
        User findUser = user.get();
        return findUser.togglePushMail();
    }

    public List<UserMailResDto> findAllPushMailsUser() {
        return userRepository.findAllByPushMail(true).stream()
                .map(UserMailResDto::of)
                .collect(Collectors.toList());
    }

    public User getNowAccessUser() {
        return userRepository.findByEmail(EMAIL)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

    }
}
