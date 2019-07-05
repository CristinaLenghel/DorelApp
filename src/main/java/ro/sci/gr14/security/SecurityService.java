package ro.sci.gr14.security;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
