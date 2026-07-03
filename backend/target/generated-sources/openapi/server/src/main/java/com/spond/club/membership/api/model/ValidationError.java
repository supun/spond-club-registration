package com.spond.club.membership.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.spond.club.membership.api.model.FieldError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ValidationError
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-03T23:30:32.738382+02:00[Europe/Oslo]", comments = "Generator version: 7.9.0")
public class ValidationError {

  private String message;

  @Valid
  private List<@Valid FieldError> fieldErrors = new ArrayList<>();

  public ValidationError() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ValidationError(String message) {
    this.message = message;
  }

  public ValidationError message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  @NotNull 
  @Schema(name = "message", example = "Validation failed", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ValidationError fieldErrors(List<@Valid FieldError> fieldErrors) {
    this.fieldErrors = fieldErrors;
    return this;
  }

  public ValidationError addFieldErrorsItem(FieldError fieldErrorsItem) {
    if (this.fieldErrors == null) {
      this.fieldErrors = new ArrayList<>();
    }
    this.fieldErrors.add(fieldErrorsItem);
    return this;
  }

  /**
   * Get fieldErrors
   * @return fieldErrors
   */
  @Valid 
  @Schema(name = "fieldErrors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fieldErrors")
  public List<@Valid FieldError> getFieldErrors() {
    return fieldErrors;
  }

  public void setFieldErrors(List<@Valid FieldError> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationError validationError = (ValidationError) o;
    return Objects.equals(this.message, validationError.message) &&
        Objects.equals(this.fieldErrors, validationError.fieldErrors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, fieldErrors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationError {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    fieldErrors: ").append(toIndentedString(fieldErrors)).append("\n");
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

