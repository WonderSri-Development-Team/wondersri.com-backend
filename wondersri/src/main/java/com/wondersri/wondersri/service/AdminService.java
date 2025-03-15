package com.wondersri.wondersri.service;

import com.wondersri.wondersri.dto.request.LoginRequestDTO;
import com.wondersri.wondersri.dto.request.RegisterRequestDTO;
import com.wondersri.wondersri.entity.Admin;

public interface AdminService {
    Admin registerAdmin(RegisterRequestDTO registerRequestDTO);
    String authenticate(LoginRequestDTO loginRequestDTO);
}