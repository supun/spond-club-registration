import {
  Container,
  Box,
  CircularProgress,
  Alert,
  Typography,
} from "@mui/material";
import { useSignupForm } from "../hooks/useSignupForm";
import { SignupWizard } from "../components/wizard/SignupWizard";
import { RegistrationClosedBanner } from "../components/RegistrationClosedBanner";
import { isRegistrationOpen } from "../utils/validation";

interface Props {
  formId: string;
}

export const SignupPage = ({ formId }: Props) => {
  const { form, loading, error } = useSignupForm(formId);

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" py={8}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Container maxWidth="lg" sx={{ py: 4 }}>
        <Alert severity="error">{error}</Alert>
      </Container>
    );
  }

  if (!form) return null;

  return (
    <> 
          <Box
      sx={{
        bgcolor: "primary.main",
        color: "white",
        py: 6,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Container maxWidth="lg" sx={{ textAlign: "center" }}>
        <Typography variant="overline" display="block">
          CLUB SIGNUP
        </Typography>

        <Typography variant="h4" component="h1" gutterBottom>
          {form.title}
        </Typography>

        <Typography
          variant="body1"
          sx={{ maxWidth: 700, mx: "auto", opacity: 0.9 }}
        >
          {form.description}
        </Typography>
      </Container>
    </Box>
 
      <Container
        maxWidth="md"
        sx={{
          py: 6,
        }}
      >
        {isRegistrationOpen(form.registrationOpens) ? (
          <SignupWizard form={form} />
        ) : (
          <RegistrationClosedBanner form={form} />
        )}
      </Container>
    </>
  );
};
