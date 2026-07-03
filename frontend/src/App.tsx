import { Alert, Container } from "@mui/material";
import { SignupPage } from "./pages/SignupPage";

function App() {
  const params = new URLSearchParams(window.location.search);
  const formId = params.get("formId");

  if (!formId) {
    return (
      <Container sx={{ py: 4 }}>
        <Alert severity="warning">
          Missing form ID. Please use a valid link
        </Alert>
      </Container>
    );
  }

  return <SignupPage formId={formId} />;
}

export default App;