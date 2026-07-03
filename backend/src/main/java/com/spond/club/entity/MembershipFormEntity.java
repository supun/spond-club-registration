package com.spond.club.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "membership_form")
public class MembershipFormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "club_id", length = 64, nullable = false)
    private String clubId;

    @NotBlank
    @Column(name = "form_id", length = 32, nullable = false, unique = true)
    private String formId;

    @NotBlank
    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @NotNull
    @Column(name = "registration_opens", nullable = false)
    private Instant registrationOpens;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "membership_form_member_type",
            joinColumns = @JoinColumn(name = "membership_form_id"),
            inverseJoinColumns = @JoinColumn(name = "member_type_id")
    )
    private Set<MemberTypeEntity> memberTypes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getRegistrationOpens() {
        return registrationOpens;
    }

    public void setRegistrationOpens(Instant registrationOpens) {
        this.registrationOpens = registrationOpens;
    }

    public Set<MemberTypeEntity> getMemberTypes() {
        return memberTypes;
    }
}
