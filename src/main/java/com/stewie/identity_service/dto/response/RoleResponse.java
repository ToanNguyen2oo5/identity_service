package com.stewie.identity_service.dto.response;

import java.util.Set;

public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse> permissions;
}
