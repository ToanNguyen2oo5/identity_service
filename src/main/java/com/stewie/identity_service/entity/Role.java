package com.stewie.identity_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Role {
    @Id
    private String id;
    private String desciption;
    @ManyToMany
    Set<Permission> permissions;
}
