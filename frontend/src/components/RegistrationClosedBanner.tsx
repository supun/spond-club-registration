import { Alert, AlertTitle, Container, Typography } from "@mui/material";
import { PublicSignupForm } from "../types/api";

interface Props {
  form: PublicSignupForm;
}

export const RegistrationClosedBanner = ({ form }: Props) => {
  const opensDate = new Date(form.registrationOpens);
  const formatted = opensDate.toLocaleString(undefined, {
    dateStyle: "long",
    timeStyle: "short",
  });

  return (
    <Container maxWidth="sm" sx={{ py: { xs: 4, sm: 8 }, px: { xs: 2, sm: 3 } }}>
      <Alert severity="info" variant="outlined">
        <AlertTitle>Registration hasn't opened yet</AlertTitle>
        <Typography variant="body2">
          Registration for <strong>{form.title}</strong> opens on{" "}
          <strong>{formatted}</strong>. Please check back then.
        </Typography>
      </Alert>
    </Container>
  );
};