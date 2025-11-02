package com.stewie.identity_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse> permissions;
}
