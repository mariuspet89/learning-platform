package eu.accesa.learningplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.learningplatform.repository.ldap.LDAPUserRepository;

@Service
public class LDAPUserService {
	
    @Autowired
    private LDAPUserRepository userRepository;

    public Boolean authenticate(String u, String p) {
        return userRepository.findByUsernameAndPassword(u, p) != null;
    }
    
}