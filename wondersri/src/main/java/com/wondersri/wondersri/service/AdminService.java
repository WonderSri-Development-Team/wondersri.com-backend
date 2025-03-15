package com.wondersri.wondersri.service;

import com.wondersri.wondersri.entity.Admin;

public interface AdminService {
    Admin registerAdmin(Admin admin);
    Admin authenticate(String username, String password);
}