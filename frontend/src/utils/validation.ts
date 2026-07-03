import { z } from "zod";

const memberTypeIdSchema = z
  .string()
  .min(1, "Please select a member type");

const nameSchema = (label: string) =>
  z
    .string()
    .trim()
    .min(1, `${label} is required`)
    .max(100, `${label} must be under 100 characters`);

const emailSchema = z
  .string()
  .trim()
  .min(1, "Email is required")
  .email("Enter a valid email address");

const phoneSchema = z
  .string()
  .trim()
  .min(1, "Phone number is required")
  .regex(/^\+?[0-9\s\-()]{7,20}$/, "Enter a valid phone number");

const birthDateSchema = z
  .string()
  .min(1, "Birth date is required")
  .refine((val) => !isNaN(new Date(val).getTime()), {
    message: "Enter a valid date",
  })
  .refine((val) => new Date(val) <= new Date(), {
    message: "Birth date cannot be in the future",
  })
  .refine(
    (val) => new Date().getFullYear() - new Date(val).getFullYear() <= 120,
    { message: "Enter a valid date" }
  );

  
// ---- Step schemas ----

export const step1Schema = z.object({
  memberTypeId: memberTypeIdSchema,
});

export const step2Schema = z.object({
  firstName: nameSchema("First name"),
  lastName: nameSchema("Last name"),
  email: emailSchema,
  phoneNumber: phoneSchema,
  birthDate: birthDateSchema,
});

export const registrationSchema = step1Schema.merge(step2Schema);

export type FormValues = z.infer<typeof registrationSchema>;
export type FormErrors = Partial<Record<keyof FormValues, string>>;

// format errors

function toFormErrors(result: z.SafeParseReturnType<any, any>): FormErrors {
  if (result.success) return {};
  const errors: FormErrors = {};
  for (const issue of result.error.issues) {
    const field = issue.path[0] as keyof FormValues;
    if (!errors[field]) {
      errors[field] = issue.message;
    }
  }
  return errors;
}

export const validateStep1 = (values: Pick<FormValues, "memberTypeId">): FormErrors =>
  toFormErrors(step1Schema.safeParse(values));

export const validateStep2 = (
  values: Omit<FormValues, "memberTypeId">
): FormErrors => toFormErrors(step2Schema.safeParse(values));

export const validateAll = (values: FormValues): FormErrors =>
  toFormErrors(registrationSchema.safeParse(values));

export const isRegistrationOpen = (registrationOpens: string): boolean => {
  return new Date(registrationOpens).getTime() <= Date.now();
};