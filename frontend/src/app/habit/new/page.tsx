"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/ui/KeycloakProvider";
import { Button, Form } from "react-bootstrap";
import * as React from "react";

type NewHabit = {
  name?: string | undefined;
  description?: string | undefined;
  schedule: "DAILY" | "WEEKLY" | "MONTHLY" | "YEARLY";
}

export default function NewHabitPage() {
  const [habit, updateHabit] = useState<NewHabit>({
    name: undefined,
    description: undefined,
    schedule: "DAILY",
  });
  const router = useRouter();
  const { initialized, authenticated, login, logout, token } = useAuth();

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    await apiFetch("/habits", token, {
      method: "POST",
      body: JSON.stringify(habit)
    });
    router.push("/");
  }

  const handleChange = (e: { target: { name: string; value: string; }; }) => {
    updateHabit({
      ...habit,
      [e.target.name]: e.target.value
    });
  }

  return (
    <main>
      <h1>Create habit</h1>
      <Form onSubmit={submit}>
        <Form.Group>
          <Form.Label>Habit name</Form.Label>
          <Form.Control type="text" name="name" value={habit.name} onChange={handleChange} placeholder="Enter habit name" />
        </Form.Group>
        <Form.Group>
          <Form.Label>Habit description</Form.Label>
          <Form.Control type="text" name="description" value={habit.description} onChange={handleChange} placeholder="Enter habit description" />
        </Form.Group>
        <Form.Group>
          <Form.Label>Schedule</Form.Label>
          <Form.Select name="schedule" value={habit.schedule} onChange={handleChange}>
            <option value="DAILY">DAILY</option>
            <option value="WEEKLY">WEEKLY</option>
            <option value="MONTHLY">MONTHLY</option>
            <option value="YEARLY">YEARLY</option>
          </Form.Select>
        </Form.Group>
        <Button type="submit">Create</Button>
      </Form>
    </main>
  );
}
