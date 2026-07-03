import { useState } from "react";
import {
  Stepper,
  Step,
  StepLabel,
  Box,
  Button,
  Paper,
  Alert,
  useMediaQuery,
  useTheme,
  CircularProgress,
} from "@mui/material";
import { PublicSignupForm } from "../../types/api";
import { StepMemberType } from "./StepMemberType";
import { StepUserInfo } from "./StepUserInfo";
import { StepPreview } from "./StepPreview";
import { FormValues, FormErrors, validateStep1, validateStep2 } from "../../utils/validation";
import { useSubmitRegistration } from "../../hooks/useSubmitRegistration";
import { RegistrationSuccessBanner } from "../RegistrationSuccessBanner";

const STEPS = ["Member Type", "Your Information", "Review & Submit"];

interface Props {
  form: PublicSignupForm;
}

export const SignupWizard = ({ form }: Props) => {
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));

  const [activeStep, setActiveStep] = useState(0);
  const [errors, setErrors] = useState<FormErrors>({});
  const [values, setValues] = useState<FormValues>({
    memberTypeId: "",
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    birthDate: "",
  });

  const { submit, submitting, validationError, success } = useSubmitRegistration(form.formId);

  const setField = <K extends keyof FormValues>(field: K, value: FormValues[K]) => {
    setValues((prev) => ({ ...prev, [field]: value }));
    if (errors[field]) {
      setErrors((prev) => ({ ...prev, [field]: undefined }));
    }
  };

  const handleNext = () => {
    if (activeStep === 0) {
      const stepErrors = validateStep1(values);
      setErrors(stepErrors);
      if (Object.keys(stepErrors).length > 0) return;
    }

    if (activeStep === 1) {
      const stepErrors = validateStep2(values);
      setErrors(stepErrors);
      if (Object.keys(stepErrors).length > 0) return;
    }

    setActiveStep((prev) => prev + 1);
  };

  const handleBack = () => setActiveStep((prev) => prev - 1);

  const handleSubmit = () => {
    submit(values);
  };

  if (success) {
      return <RegistrationSuccessBanner />;
    ;
  }

  return (
    <Paper
      elevation={isMobile ? 0 : 3}
      sx={{
        width: "100%",
        borderRadius: { xs: 0, sm: 2 },
        p: { xs: 2, sm: 4 },
      }}
    >
      <Stepper
        activeStep={activeStep}
        alternativeLabel={!isMobile}
        orientation={isMobile ? "vertical" : "horizontal"}
        sx={{ mb: 4 }}
      >
        {STEPS.map((label) => (
          <Step key={label}>
            <StepLabel>{label}</StepLabel>
          </Step>
        ))}
      </Stepper>

      <Box sx={{ minHeight: 240 }}>
        {activeStep === 0 && (
          <StepMemberType
            form={form}
            memberTypeId={values.memberTypeId}
            onChange={(v) => setField("memberTypeId", v)}
            errors={errors}
          />
        )}

        {activeStep === 1 && (
          <StepUserInfo
            values={values}
            onChange={(field, v) => setField(field, v)}
            errors={errors}
          />
        )}

        {activeStep === 2 && (
          <StepPreview form={form} values={values} validationError={validationError} />
        )}
      </Box>

      <Box
        sx={{
          display: "flex",
          flexDirection: { xs: "column-reverse", sm: "row" },
          justifyContent: "space-between",
          gap: 2,
          mt: 4,
        }}
      >
        <Button
          onClick={handleBack}
          disabled={activeStep === 0 || submitting}
          fullWidth={isMobile}
        >
          Back
        </Button>

        {activeStep < STEPS.length - 1 ? (
          <Button variant="contained" onClick={handleNext} fullWidth={isMobile}>
            Next
          </Button>
        ) : (
          <Button
            variant="contained"
            onClick={handleSubmit}
            disabled={submitting}
            fullWidth={isMobile}
          >
            {submitting ? <CircularProgress size={24} color="inherit" /> : "Submit"}
          </Button>
        )}
      </Box>
    </Paper>
  );
};