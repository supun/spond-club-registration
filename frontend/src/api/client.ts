
const BASE_URL = import.meta.env.VITE_API_BASE_URL;
import {
  CreateRegistrationRequest,
  PublicSignupForm,
  RegistrationSubmissionResponse,
} from "../types/api";


async function apiFetch<T>(path: string, options?: RequestInit): Promise<T> {
  const res = await fetch(`${BASE_URL}${path}`, {
    headers: { "Content-Type": "application/json" },
    ...options,
  });

  if (!res.ok) {
    const errorBody = await res.json().catch(() => null);
    throw { status: res.status, body: errorBody };
  }

  return res.json() as Promise<T>;
}

export const getSignupForm = (formId: string) =>
  apiFetch<PublicSignupForm>(`/api/forms/${formId}`);

export const submitRegistration = (formId: string, data: CreateRegistrationRequest) =>
  apiFetch<RegistrationSubmissionResponse>(`/api/forms/${formId}/submissions`, {
    method: "POST",
    body: JSON.stringify(data),
  });