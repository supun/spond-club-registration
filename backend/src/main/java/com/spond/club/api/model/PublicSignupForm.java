package com.spond.club.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spond.club.membership.api.model.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PublicSignupForm
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-03T21:14:39.340753+02:00[Europe/Oslo]", comments = "Generator version: 7.9.0")
public class PublicSignupForm {

  private String clubId;

  @Valid
  private List<@Valid MemberType> memberTypes = new ArrayList<>();

  private String formId;

  private String title;

  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime registrationOpens;

  public PublicSignupForm() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PublicSignupForm(String clubId, List<@Valid MemberType> memberTypes, String formId, String title, OffsetDateTime registrationOpens) {
    this.clubId = clubId;
    this.memberTypes = memberTypes;
    this.formId = formId;
    this.title = title;
    this.registrationOpens = registrationOpens;
  }

  public PublicSignupForm clubId(String clubId) {
    this.clubId = clubId;
    return this;
  }

  /**
   * Get clubId
   * @return clubId
   */
  @NotNull 
  @Schema(name = "clubId", example = "britsport", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("clubId")
  public String getClubId() {
    return clubId;
  }

  public void setClubId(String clubId) {
    this.clubId = clubId;
  }

  public PublicSignupForm memberTypes(List<@Valid MemberType> memberTypes) {
    this.memberTypes = memberTypes;
    return this;
  }

  public PublicSignupForm addMemberTypesItem(MemberType memberTypesItem) {
    if (this.memberTypes == null) {
      this.memberTypes = new ArrayList<>();
    }
    this.memberTypes.add(memberTypesItem);
    return this;
  }

  /**
   * Get memberTypes
   * @return memberTypes
   */
  @NotNull @Valid 
  @Schema(name = "memberTypes", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("memberTypes")
  public List<@Valid MemberType> getMemberTypes() {
    return memberTypes;
  }

  public void setMemberTypes(List<@Valid MemberType> memberTypes) {
    this.memberTypes = memberTypes;
  }

  public PublicSignupForm formId(String formId) {
    this.formId = formId;
    return this;
  }

  /**
   * Get formId
   * @return formId
   */
  @NotNull 
  @Schema(name = "formId", example = "B171388180BC457D9887AD92B6CCFC86", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("formId")
  public String getFormId() {
    return formId;
  }

  public void setFormId(String formId) {
    this.formId = formId;
  }

  public PublicSignupForm title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", example = "Coding camp summer 2025", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PublicSignupForm description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", example = "Coding camp summer 2025 signup form", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PublicSignupForm registrationOpens(OffsetDateTime registrationOpens) {
    this.registrationOpens = registrationOpens;
    return this;
  }

  /**
   * Get registrationOpens
   * @return registrationOpens
   */
  @NotNull @Valid 
  @Schema(name = "registrationOpens", example = "2024-12-16T00:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("registrationOpens")
  public OffsetDateTime getRegistrationOpens() {
    return registrationOpens;
  }

  public void setRegistrationOpens(OffsetDateTime registrationOpens) {
    this.registrationOpens = registrationOpens;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicSignupForm publicSignupForm = (PublicSignupForm) o;
    return Objects.equals(this.clubId, publicSignupForm.clubId) &&
        Objects.equals(this.memberTypes, publicSignupForm.memberTypes) &&
        Objects.equals(this.formId, publicSignupForm.formId) &&
        Objects.equals(this.title, publicSignupForm.title) &&
        Objects.equals(this.description, publicSignupForm.description) &&
        Objects.equals(this.registrationOpens, publicSignupForm.registrationOpens);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clubId, memberTypes, formId, title, description, registrationOpens);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublicSignupForm {\n");
    sb.append("    clubId: ").append(toIndentedString(clubId)).append("\n");
    sb.append("    memberTypes: ").append(toIndentedString(memberTypes)).append("\n");
    sb.append("    formId: ").append(toIndentedString(formId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    registrationOpens: ").append(toIndentedString(registrationOpens)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

