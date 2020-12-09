package eu.accesa.learningplatform.repository.ldap;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import eu.accesa.learningplatform.model.entity.ldap.User;

@Repository
public interface LDAPUserRepository extends LdapRepository<User> {
    User findByUsernameAndPassword(String username, String password);
}