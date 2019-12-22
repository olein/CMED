package com.cmed.service;

import com.cmed.dto.*;
import com.cmed.entity.Prescription;

import java.util.List;

public interface UserService {

    LoginResponse loginUser(LoginRequest loginRequest);
}
