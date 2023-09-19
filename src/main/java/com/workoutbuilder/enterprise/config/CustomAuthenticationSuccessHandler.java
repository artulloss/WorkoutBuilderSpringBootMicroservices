package com.workoutbuilder.enterprise.config;

import com.workoutbuilder.enterprise.entity.User;
import com.workoutbuilder.enterprise.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom Authentication Success Handler.
 * Responsible for handling successful user authentication.
 * Upon a successful login, users are redirected to their respective dashboards based on their unique ID.
 * If the user is not found, the default success URL of the superclass is used.
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private IUserService IuserService;

    /**
     * Handles the redirect logic for authenticated users.
     * Upon successful authentication, it fetches the authenticated user's details using their email.
     * If the user exists, they are redirected to their specific dashboard URL.
     * Otherwise, the superclass's success URL is used.
     *
     * @param request        the HTTP request.
     * @param response       the HTTP response.
     * @param authentication the current authentication object.
     * @throws IOException      if an IO error occurs.
     * @throws ServletException if a Servlet error occurs.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userEmail = authentication.getName();
        User user = IuserService.findByEmail(userEmail);

        if (user != null) {
            String redirectUrl = "/" + user.getId() + "/dashboard";
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
