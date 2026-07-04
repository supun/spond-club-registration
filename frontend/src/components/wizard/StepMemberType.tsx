import { Control, Controller, FieldErrors } from "react-hook-form";
import { TextField, MenuItem, Stack } from "@mui/material";
import { PublicSignupForm } from "../../types/api";
import { FormValues } from "../../utils/validation";

interface Props {
  form: PublicSignupForm;
  control: Control<FormValues>;
  errors: FieldErrors<FormValues>;
}

export const StepMemberType = ({ form, control, errors }: Props) => {
  return (
    <Stack spacing={2}>
      <Controller
        name="memberTypeId"
        control={control}
        rules={{ required: "Member type is required" }}
        render={({ field }) => (
          <TextField
            {...field}
            select
            label="Member Type"
            error={!!errors.memberTypeId}
            helperText={errors.memberTypeId?.message ?? " "}
            fullWidth
            required
          >
            {form.memberTypes.map((mt) => (
              <MenuItem key={mt.id} value={mt.id}>
                {mt.name}
              </MenuItem>
            ))}
          </TextField>
        )}
      />
    </Stack>
  );
};