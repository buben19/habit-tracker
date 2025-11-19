"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/app/ui/KeycloakProvider";

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
    <main style={{ padding: 24 }}>
      <h1>Create habit</h1>
      <form onSubmit={submit} style={{ display: "flex", flexDirection: "column", gap: 12, maxWidth: 300 }}>
        <input value={name} onChange={e => setName(e.target.value)} placeholder="Habit name" />
        <input value={schedule} onChange={e => setSchedule(e.target.value)} placeholder="Schedule" />
        <button type="submit">Create</button>
      </form>
    </main>
  );
}
