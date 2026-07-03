import { useState } from "react";
import { CreateRegistrationRequest, ValidationError } from "../types/api";
import { submitRegistration } from "../api/client";

export function useSubmitRegistration(formId: string) {
  const [submitting, setSubmitting] = useState(false);
  const [validationError, setValidationError] = useState<ValidationError | null>(null);
  const [success, setSuccess] = useState(false);

  const submit = async (data: CreateRegistrationRequest) => {
    setSubmitting(true);
    setValidationError(null);
    try {
      await submitRegistration(formId, data);
      setSuccess(true);
    } catch (err: any) {
      if (err.status === 400) {
        setValidationError(err.body as ValidationError);
      } else {
        setValidationError({ message: "Something went wrong. Please try again." });
      }
    } finally {
      setSubmitting(false);
    }
  };

  return { submit, submitting, validationError, success };
}