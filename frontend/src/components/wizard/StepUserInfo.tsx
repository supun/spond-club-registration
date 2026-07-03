import { Stack, TextField } from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import { FormValues, FormErrors } from "../../utils/validation";

interface Props {
  values: Omit<FormValues, "memberTypeId">;
  onChange: (field: keyof Omit<FormValues, "memberTypeId">, value: string) => void;
  errors: FormErrors;
}

export const StepUserInfo = ({ values, onChange, errors }: Props) => {
  return (
    <Stack spacing={2}>
      <Stack direction={{ xs: "column", sm: "row" }} spacing={2}>
        <TextField
          label="First Name"
          value={values.firstName}
          onChange={(e) => onChange("firstName", e.target.value)}
          error={!!errors.firstName}
          helperText={errors.firstName ?? " "}
          fullWidth
          required
        />
        <TextField
          label="Last Name"
          value={values.lastName}
          onChange={(e) => onChange("lastName", e.target.value)}
          error={!!errors.lastName}
          helperText={errors.lastName ?? " "}
          fullWidth
          required
        />
      </Stack>

      <TextField
        label="Email"
        type="email"
        value={values.email}
        onChange={(e) => onChange("email", e.target.value)}
        error={!!errors.email}
        helperText={errors.email ?? " "}
        fullWidth
        required
      />

      <Stack direction={{ xs: "column", sm: "row" }} spacing={2}>
        <TextField
          label="Phone Number"
          type="tel"
          value={values.phoneNumber}
          onChange={(e) => onChange("phoneNumber", e.target.value)}
          error={!!errors.phoneNumber}
          helperText={errors.phoneNumber ?? " "}
          fullWidth
          required
        />
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="Birth Date"
            value={values.birthDate ? dayjs(values.birthDate) : null}
            onChange={(date) =>
              onChange(
                "birthDate",
                date?.isValid() ? date.format("YYYY-MM-DD") : ""
              )
            }
            maxDate={dayjs()}
            slotProps={{
              textField: {
                error: !!errors.birthDate,
                helperText: errors.birthDate ?? " ",
                fullWidth: true,
                required: true,
              },
            }}
          />
        </LocalizationProvider>
      </Stack>
    </Stack>
  );
};
