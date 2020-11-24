
package eu.accesa.learningplatform.security;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapAuthentication {
    public static void main(String[] args) {
        Hashtable<String, String> login = new Hashtable<>();
        login.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        login.put(Context.PROVIDER_URL, "ldap://10.115.1.200/OU=ACCESA_Users,DC=accesa,DC=eu");
        login.put(Context.SECURITY_AUTHENTICATION, "simple");
        login.put(Context.SECURITY_PRINCIPAL,"accesa\\java.internship");
        login.put(Context.SECURITY_CREDENTIALS, "A@cc35@!2020");

        try {
            DirContext authContext = new InitialDirContext(login);
            System.out.println("User is authenticated");
            authContext.close();
        } catch (AuthenticationException ae) {
            System.out.println("Authentication failed" + ae);
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }
}

