import { Box, Typography, TextField, MenuItem, Stack } from "@mui/material";
import { PublicSignupForm } from "../../types/api";
import { FormErrors } from "../../utils/validation";

interface Props {
  form: PublicSignupForm;
  memberTypeId: string;
  onChange: (value: string) => void;
  errors: FormErrors;
}

export const StepMemberType = ({ form, memberTypeId, onChange, errors }: Props) => {
  return (
    <Stack spacing={2}>

      <TextField
        select
        label="Member Type"
        value={memberTypeId}
        onChange={(e) => onChange(e.target.value)}
        error={!!errors.memberTypeId}
        helperText={errors.memberTypeId ?? " "}
        fullWidth
        required
      >
        {form.memberTypes.map((mt) => (
          <MenuItem key={mt.id} value={mt.id}>
            {mt.name}
          </MenuItem>
        ))}
      </TextField>
    </Stack>
  );
};