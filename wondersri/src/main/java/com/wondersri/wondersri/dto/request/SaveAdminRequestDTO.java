package com.wondersri.wondersri.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SaveAdminRequestDTO {
    private Long id;
    private String username;
    private String role;
}