import { useEffect, useState } from "react";
import { PublicSignupForm } from "../types/api";
import { getSignupForm } from "../api/client";

export function useSignupForm(formId: string) {
  const [form, setForm] = useState<PublicSignupForm | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
   async function loadForm() {
        setLoading(true);
        setError(null);

        try {
          setForm(await getSignupForm(formId));
        } catch {
          setError("Failed to load form");
          setForm(null);
        } finally {
          setLoading(false);
        }
      }

    loadForm();
  }, [formId]);

  return { form, loading, error };
}