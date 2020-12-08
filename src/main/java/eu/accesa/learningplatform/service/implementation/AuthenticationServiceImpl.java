package eu.accesa.learningplatform.service.implementation;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.accesa.learningplatform.authentication.AccessToken;
import eu.accesa.learningplatform.authentication.AuthRequest;
import eu.accesa.learningplatform.authentication.OAuth2Constants;
import eu.accesa.learningplatform.authentication.PrivateClaimType;
import eu.accesa.learningplatform.repository.CourseRepository;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.service.AuthenticationService;
import eu.accesa.learningplatform.service.TokenService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final TokenService tokenService;

	public AuthenticationServiceImpl(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Transactional
	public AccessToken authenticate(AuthRequest authRequest) {
		return loginWithPassword(authRequest);
	}

	private AccessToken loginWithPassword(AuthRequest authRequest) {

		//authentication !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//verify user in LDAP
		String email = "";//get from LDAP
		String userName = "";
		
		Map<String, String> privateClaimMap = privateClaims(email);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expirationTime = now.plus(1, ChronoUnit.DAYS);

		return getAccessToken(now, expirationTime, privateClaimMap, userName, email);
	}

	private AccessToken getAccessToken(LocalDateTime now, LocalDateTime expirationTime,
			Map<String, String> privateClaimMap, String userName, String email) {
		return new AccessToken().accessToken(tokenService.generateJwtToken(userName, privateClaimMap, null))
				.tokenType(OAuth2Constants.BEARER_TYPE).active(true).refreshToken(tokenService.getRefreshToken(email))
				.expiresAt(java.sql.Date.valueOf(expirationTime.toLocalDate()))
				.expiresIn(Period.between(now.toLocalDate(), expirationTime.toLocalDate()).getDays())
				.expiresIn(ChronoUnit.SECONDS.between(now, expirationTime));
	}

    private static Map<String, String> privateClaims(String email) {
        Map<String, String> privateClaimMap = new HashMap<>();
        if (email != null) {
            privateClaimMap.put(PrivateClaimType.MAIL.getType(), email);
        }

        return privateClaimMap;
    }

    
}
