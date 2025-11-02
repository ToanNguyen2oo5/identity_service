package com.stewie.identity_service.mapper;

import com.stewie.identity_service.dto.request.PermissionRequest;
import com.stewie.identity_service.dto.response.PermissionResponse;
import com.stewie.identity_service.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
