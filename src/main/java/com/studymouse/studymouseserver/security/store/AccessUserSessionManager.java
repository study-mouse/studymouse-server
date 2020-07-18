package com.studymouse.studymouseserver.security.store;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessUserSessionManager implements AccessUserManager {

    private static String USER_SESSION_KEY = "LOGIN_ACCOUNT";
    private final HttpSession httpSession;

    @Override
    public boolean hasAuthentication() {
        return httpSession.getAttribute(USER_SESSION_KEY) != null;
    }

    @Override
    public AccessUser getAccessUser() {
        AccessUser accessUser = (AccessUser) httpSession.getAttribute(USER_SESSION_KEY);

        if (accessUser == null) {
            throw new IllegalArgumentException("로그인 정보가 없습니다.");
        }

        return accessUser;
    }

    @Override
    public void saveSession(final AccessUser accessUser) {
        httpSession.setAttribute(USER_SESSION_KEY, accessUser);
    }

    @Override
    public void deleteSession() {
        if (hasAuthentication()) {
            httpSession.removeAttribute(USER_SESSION_KEY);
        }

        throw new IllegalArgumentException("로그인 정보가 없습니다.");
    }
}
