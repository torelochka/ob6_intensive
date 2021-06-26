package ru.ob6.web.security.handlers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.ob6.api.dto.UserDto;
import ru.ob6.web.security.details.UserDetailsImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SuccessAuthHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        session.setAttribute("user", modelMapper.map(userDetails.getUser(), UserDto.class));

        httpServletResponse.sendRedirect("/films");
    }
}
