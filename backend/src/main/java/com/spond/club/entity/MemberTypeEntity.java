package com.spond.club.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "member_type")
public class MemberTypeEntity {

    @Id
    @NotBlank
    @Column(name = "id", length = 32, nullable = false, updatable = false)
    private String id;

    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
