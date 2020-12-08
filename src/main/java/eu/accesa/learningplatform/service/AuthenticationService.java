package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.authentication.AccessToken;
import eu.accesa.learningplatform.authentication.AuthRequest;

public interface AuthenticationService {

	public AccessToken authenticate(AuthRequest authRequest);
	
	public boolean verifyToken(String token);
	
}
