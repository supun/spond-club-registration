import { useForm } from "react-hook-form";
import {
  Stepper,
  Step,
  StepLabel,
  Box,
  Button,
  Paper,
  useMediaQuery,
  useTheme,
  CircularProgress,
} from "@mui/material";
import { PublicSignupForm } from "../../types/api";
import { StepMemberType } from "./StepMemberType";
import { StepUserInfo } from "./StepUserInfo";
import { StepPreview } from "./StepPreview";
import { FormValues } from "../../utils/validation";
import { useSubmitRegistration } from "../../hooks/useSubmitRegistration";
import { RegistrationSuccessBanner } from "../RegistrationSuccessBanner";
import { useState } from "react";

const STEPS = ["Member Type", "Your Information", "Review & Submit"];

const STEP_FIELDS: (keyof FormValues)[][] = [
  ["memberTypeId"],
  ["firstName", "lastName", "email", "phoneNumber", "birthDate"],
  [],
];

interface Props {
  form: PublicSignupForm;
}

export const SignupWizard = ({ form }: Props) => {
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));
  const [activeStep, setActiveStep] = useState(0);

  const {
    control,
    handleSubmit,
    trigger,
    getValues,
    formState: { errors },
  } = useForm<FormValues>({
    mode: "onChange",
    defaultValues: {
      memberTypeId: "",
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      birthDate: "",
    },
  });

  const { submit, submitting, validationError, success } = useSubmitRegistration(form.formId);

  const handleNext = async () => {
    const valid = await trigger(STEP_FIELDS[activeStep]);
    if (!valid) return;
    setActiveStep((prev) => prev + 1);
  };

  const handleBack = () => setActiveStep((prev) => prev - 1);

  const onSubmit = (values: FormValues) => {
    submit(values);
  };

  if (success) {
    return <RegistrationSuccessBanner />;
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
        {activeStep === 0 && <StepMemberType form={form} control={control} errors={errors} />}
        {activeStep === 1 && <StepUserInfo control={control} errors={errors} />}
        {activeStep === 2 && (
          <StepPreview form={form} values={getValues()} validationError={validationError} />
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
        <Button onClick={handleBack} disabled={activeStep === 0 || submitting} fullWidth={isMobile}>
          Back
        </Button>
        {activeStep < STEPS.length - 1 ? (
          <Button variant="contained" onClick={handleNext} fullWidth={isMobile}>
            Next
          </Button>
        ) : (
          <Button
            variant="contained"
            onClick={handleSubmit(onSubmit)}
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