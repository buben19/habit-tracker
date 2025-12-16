"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/ui/KeycloakProvider";
import { Button, Form } from "react-bootstrap";

export default function NewHabitPage() {
  const [name, setName] = useState("");
  const [schedule, setSchedule] = useState("DAILY");
  const router = useRouter();
  const { initialized, authenticated, login, logout, token } = useAuth();

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    await apiFetch("/habits", token, {
      method: "POST",
      body: JSON.stringify({ name, schedule })
    });
    router.push("/");
  }

  return (
    <main>
      <h1>Create habit</h1>
      <Form onSubmit={submit}>
        <Form.Group>
          <Form.Label>Habit name</Form.Label>
          <Form.Control type="text" value={name} onChange={e => setName(e.target.value)} placeholder="Enter habit name" />
          <Form.Text>adsd</Form.Text>
        </Form.Group>
        <Form.Group>
          <Form.Label>Schedule</Form.Label>
          <Form.Select value={schedule} onChange={e => setSchedule(e.target.value)}>
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
