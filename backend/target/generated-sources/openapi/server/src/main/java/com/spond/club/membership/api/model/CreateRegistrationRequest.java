package com.spond.club.membership.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateRegistrationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-03T23:30:32.738382+02:00[Europe/Oslo]", comments = "Generator version: 7.9.0")
public class CreateRegistrationRequest {

  private String memberTypeId;

  private String firstName;

  private String lastName;

  private String email;

  private String phoneNumber;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthDate;

  public CreateRegistrationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateRegistrationRequest(String memberTypeId, String firstName, String lastName, String email, String phoneNumber, LocalDate birthDate) {
    this.memberTypeId = memberTypeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.birthDate = birthDate;
  }

  public CreateRegistrationRequest memberTypeId(String memberTypeId) {
    this.memberTypeId = memberTypeId;
    return this;
  }

  /**
   * Get memberTypeId
   * @return memberTypeId
   */
  @NotNull 
  @Schema(name = "memberTypeId", example = "8FE4113D4E4020E0DCF887803A886981", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("memberTypeId")
  public String getMemberTypeId() {
    return memberTypeId;
  }

  public void setMemberTypeId(String memberTypeId) {
    this.memberTypeId = memberTypeId;
  }

  public CreateRegistrationRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "firstName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public CreateRegistrationRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "lastName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CreateRegistrationRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @NotNull @Size(min = 1) @jakarta.validation.constraints.Email 
  @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CreateRegistrationRequest phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "phoneNumber", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("phoneNumber")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public CreateRegistrationRequest birthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Get birthDate
   * @return birthDate
   */
  @NotNull @Valid 
  @Schema(name = "birthDate", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("birthDate")
  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateRegistrationRequest createRegistrationRequest = (CreateRegistrationRequest) o;
    return Objects.equals(this.memberTypeId, createRegistrationRequest.memberTypeId) &&
        Objects.equals(this.firstName, createRegistrationRequest.firstName) &&
        Objects.equals(this.lastName, createRegistrationRequest.lastName) &&
        Objects.equals(this.email, createRegistrationRequest.email) &&
        Objects.equals(this.phoneNumber, createRegistrationRequest.phoneNumber) &&
        Objects.equals(this.birthDate, createRegistrationRequest.birthDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberTypeId, firstName, lastName, email, phoneNumber, birthDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateRegistrationRequest {\n");
    sb.append("    memberTypeId: ").append(toIndentedString(memberTypeId)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
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

