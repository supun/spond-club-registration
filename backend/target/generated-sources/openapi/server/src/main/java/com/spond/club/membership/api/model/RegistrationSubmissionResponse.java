package com.spond.club.membership.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RegistrationSubmissionResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-03T23:30:32.738382+02:00[Europe/Oslo]", comments = "Generator version: 7.9.0")
public class RegistrationSubmissionResponse {

  private String formId;

  private Boolean submitted;

  private String message;

  public RegistrationSubmissionResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RegistrationSubmissionResponse(String formId, Boolean submitted) {
    this.formId = formId;
    this.submitted = submitted;
  }

  public RegistrationSubmissionResponse formId(String formId) {
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

  public RegistrationSubmissionResponse submitted(Boolean submitted) {
    this.submitted = submitted;
    return this;
  }

  /**
   * Get submitted
   * @return submitted
   */
  @NotNull 
  @Schema(name = "submitted", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("submitted")
  public Boolean getSubmitted() {
    return submitted;
  }

  public void setSubmitted(Boolean submitted) {
    this.submitted = submitted;
  }

  public RegistrationSubmissionResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", example = "Registration submitted successfully.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationSubmissionResponse registrationSubmissionResponse = (RegistrationSubmissionResponse) o;
    return Objects.equals(this.formId, registrationSubmissionResponse.formId) &&
        Objects.equals(this.submitted, registrationSubmissionResponse.submitted) &&
        Objects.equals(this.message, registrationSubmissionResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(formId, submitted, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrationSubmissionResponse {\n");
    sb.append("    formId: ").append(toIndentedString(formId)).append("\n");
    sb.append("    submitted: ").append(toIndentedString(submitted)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

