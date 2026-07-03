import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

export const RegistrationSuccessBanner = () => (
  <Paper
    elevation={0}
    sx={{
      p: 4,
      textAlign: "center",
      bgcolor: "success.light",
      color: "success.contrastText",
      borderRadius: 2,
    }}
  >
    <Typography variant="h5" gutterBottom>
      Registration submitted
    </Typography>

    <Typography>
      Thank you! Your registration has been submitted successfully.
    </Typography>
  </Paper>
);