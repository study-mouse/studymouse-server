package com.studymouse.studymouseserver.security.store;

import com.studymouse.studymouseserver.security.dto.AccessUser;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AccessUserManager {
    boolean hasAuthentication();

    AccessUser getAccessUser();

    void saveSession(AccessUser accessUser);

    void deleteSession();
}
