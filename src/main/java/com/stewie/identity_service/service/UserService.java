package com.stewie.identity_service.service;

import com.stewie.identity_service.dto.request.UserCreationRequest;
import com.stewie.identity_service.dto.request.UserUpdateRequest;
import com.stewie.identity_service.dto.response.UserResponse;
import com.stewie.identity_service.entity.User;
import com.stewie.identity_service.enums.Role;
import com.stewie.identity_service.exception.AppException;
import com.stewie.identity_service.exception.ErrorCode;
import com.stewie.identity_service.mapper.UserMapper;
import com.stewie.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepository  userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user= userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles=new HashSet<>();
        roles.add(Role.USER.name());
       // user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));

    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().
                map(userMapper::toUserResponse).collect(Collectors.toList());
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(@PathVariable String userId){
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
    User user =userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("user not found"));
    userMapper.updateUser(user,request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);

    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(
                ()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }
}
