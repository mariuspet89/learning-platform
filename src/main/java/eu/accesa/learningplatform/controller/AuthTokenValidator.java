package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.service.TokenService;
import eu.accesa.learningplatform.util.AuthUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenValidator implements Filter {

    private final TokenService tokenService;

    @Autowired
    public AuthTokenValidator(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String authorization = httpServletRequest.getHeader("Authorization");
        String bearer = AuthUtils.extractBearerToken(authorization);

        boolean userHasAuthorisation = false;

        if (StringUtils.isNotEmpty(authorization)) {
            if (tokenService.verifyToken(bearer)) {
            	userHasAuthorisation = true;
            } 
        }
        if (!(httpServletRequest.getRequestURL().indexOf("/authentication/token") == -1)) {
            userHasAuthorisation = true;
        }
        if (!(httpServletRequest.getRequestURL().indexOf("/swagger-ui/index.html") == -1)) {
            userHasAuthorisation = true;
        }
        if (userHasAuthorisation) {
        	filterChain.doFilter(request, httpServletResponse);
        } else {
        	httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized. Please login before making a request.");
        }
    }

    @Override
    public void destroy() {
    }
}