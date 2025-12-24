package task16_17_19.service;

import task16_17_19.dto.LoginDto;

public interface AuthService {
    boolean authenticate(LoginDto loginDto);
}
