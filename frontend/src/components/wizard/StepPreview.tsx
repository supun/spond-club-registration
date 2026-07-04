import { Stack, Typography, Divider, Box, Alert } from "@mui/material";
import { PublicSignupForm, ValidationError } from "../../types/api";
import { FormValues } from "../../utils/validation";

interface Props {
  form: PublicSignupForm;
  values: FormValues;
  validationError: ValidationError | null;
}

const Row = ({ label, value }: { label: string; value: string }) => (
  <Box sx={{ display: "flex", justifyContent: "space-between", py: 0.75 }}>
    <Typography variant="body2" color="text.secondary">
      {label}
    </Typography>
    <Typography variant="body2" sx={{ fontWeight: 500, textAlign: "right" }}>
      {value || "—"}
    </Typography>
  </Box>
);

export const StepPreview = ({ form, values, validationError }: Props) => {
  const memberTypeName =
    form.memberTypes.find((mt) => mt.id === values.memberTypeId)?.name ?? "—";

  return (
    <Stack spacing={2}>
      <Typography variant="h6">Review your information</Typography>

      {validationError && (
        <Alert severity="error">
          {validationError.fieldErrors?.[0]?.message ??
            validationError.message ??
            "Something went wrong."}
        </Alert>
      )}

      <Box>
        <Row label="Member Type" value={memberTypeName} />
        <Divider />
        <Row label="First Name" value={values.firstName} />
        <Divider />
        <Row label="Last Name" value={values.lastName} />
        <Divider />
        <Row label="Email" value={values.email} />
        <Divider />
        <Row label="Phone Number" value={values.phoneNumber} />
        <Divider />
        <Row label="Birth Date" value={values.birthDate} />
      </Box>
    </Stack>
  );
};