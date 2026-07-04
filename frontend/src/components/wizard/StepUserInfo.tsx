import { Control, Controller, FieldErrors } from "react-hook-form";
import { Stack, TextField } from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import { FormValues } from "../../utils/validation";

interface Props {
  control: Control<FormValues>;
  errors: FieldErrors<FormValues>;
}

export const StepUserInfo = ({ control, errors }: Props) => {
  return (
    <Stack spacing={2}>
      <Stack direction={{ xs: "column", sm: "row" }} spacing={2}>
        <Controller
          name="firstName"
          control={control}
          rules={{ required: "First name is required" }}
          render={({ field }) => (
            <TextField
              {...field}
              label="First Name"
              error={!!errors.firstName}
              helperText={errors.firstName?.message ?? " "}
              fullWidth
              required
            />
          )}
        />
        <Controller
          name="lastName"
          control={control}
          rules={{ required: "Last name is required" }}
          render={({ field }) => (
            <TextField
              {...field}
              label="Last Name"
              error={!!errors.lastName}
              helperText={errors.lastName?.message ?? " "}
              fullWidth
              required
            />
          )}
        />
      </Stack>

      <Controller
        name="email"
        control={control}
        rules={{
          required: "Email is required",
          pattern: { value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/, message: "Invalid email address" },
        }}
        render={({ field }) => (
          <TextField
            {...field}
            label="Email"
            type="email"
            error={!!errors.email}
            helperText={errors.email?.message ?? " "}
            fullWidth
            required
          />
        )}
      />

      <Stack direction={{ xs: "column", sm: "row" }} spacing={2}>
        <Controller
          name="phoneNumber"
          control={control}
          rules={{ required: "Phone number is required" }}
          render={({ field }) => (
            <TextField
              {...field}
              label="Phone Number"
              type="tel"
              error={!!errors.phoneNumber}
              helperText={errors.phoneNumber?.message ?? " "}
              fullWidth
              required
            />
          )}
        />

        <Controller
          name="birthDate"
          control={control}
          rules={{ required: "Birth date is required" }}
          render={({ field }) => (
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="Birth Date"
                value={field.value ? dayjs(field.value) : null}
                onChange={(date) =>
                  field.onChange(date?.isValid() ? date.format("YYYY-MM-DD") : "")
                }
                maxDate={dayjs()}
                slotProps={{
                  textField: {
                    error: !!errors.birthDate,
                    helperText: errors.birthDate?.message ?? " ",
                    fullWidth: true,
                    required: true,
                    onBlur: field.onBlur,
                  },
                }}
              />
            </LocalizationProvider>
          )}
        />
      </Stack>
    </Stack>
  );
};