package task16_17_19.service;

import task16_17_19.dto.LoginDto;

public class AuthServiceImpl implements AuthService {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    @Override
    public boolean authenticate(LoginDto loginDto) {
        if (USERNAME.equals(loginDto.username()) && PASSWORD.equals(loginDto.password()))
        {
            return true;
    }else return false;
    }
}
