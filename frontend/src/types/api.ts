export interface MemberType {
  id: string;
  name: string;
}

export interface PublicSignupForm {
  clubId: string;
  memberTypes: MemberType[];
  formId: string;
  title: string;
  description?: string;
  registrationOpens: string;
}

export interface CreateRegistrationRequest {
  memberTypeId: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  birthDate: string; // ISO date (yyyy-MM-dd)
}

export interface RegistrationSubmissionResponse {
  formId: string;
  submitted: boolean;
  message?: string;
}

export interface ApiError {
  message: string;
}

export interface FieldError {
  field: string;
  message: string;
}

export interface ValidationError {
  message: string;
  fieldErrors?: FieldError[];
}