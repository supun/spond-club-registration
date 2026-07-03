import { test, expect } from "@playwright/test";

test("user can submit signup form", async ({ page }) => {
  await page.goto("http://localhost:3000?formId=B171388180BC457D9887AD92B6CCFC86");

  await page.getByRole("combobox", { name: "Member Type" }).click();
  await page.getByRole("option", { name: "Active Member" }).click();
  await page.getByRole("button", { name: "Next" }).click();

  await page.getByLabel("First name").fill("John");
  await page.getByLabel("Last name").fill("Doe");
 await page.getByLabel("Email").fill(`john-${Date.now()}@test.com`); // generate unique email for each test, otherwise this test will fail
  await page.getByLabel("Phone number").fill("12345678");
  await page.getByLabel("Birth Date").fill("01/01/2000");
  await page.getByRole("button", { name: "Next" }).click();

  await page.getByRole("button", { name: "Submit" }).click();

  await expect(page.getByText("Registration submitted")).toBeVisible();
});