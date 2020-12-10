package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.service.TokenService;
import eu.accesa.learningplatform.util.AuthUtils;
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

        //TODO: Treat empty authorisation header allowing requests to be made

        if (authorization != null) {
            if (!tokenService.verifyToken(bearer)) {
                httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "Invalid token. Please login again");
            }
        }

        if (httpServletRequest.getRequestURL().indexOf("/authentication/token") == -1) {
            userHasAuthorisation = true;
        }

        if (httpServletRequest.getRequestURL().indexOf("/swagger-ui/index.html") == -1) {
            userHasAuthorisation = true;
        }

        if (!userHasAuthorisation) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized. Please login before making a request.");
        }
        filterChain.doFilter(request, httpServletResponse);
    }

    @Override
    public void destroy() {
    }
}