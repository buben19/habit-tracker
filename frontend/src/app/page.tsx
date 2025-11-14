"use client";

import { useEffect, useState } from "react";
import { apiFetch } from "@/lib/api";

export default function HomePage() {
  const [habits, setHabits] = useState<any[]>([]);

  useEffect(() => {
    apiFetch("/habits")
      .then(setHabits)
      .catch((e) => console.error(e));
  }, []);

  async function mark(habitId: number) {
    await apiFetch("/checkins", {
      method: "POST",
      body: JSON.stringify({ habitId })
    });
    alert("Marked!");
  }

  return (
    <main style={{ padding: 24 }}>
      <h1 style={{ fontSize: 28, fontWeight: 700 }}>Today&apos;s habits</h1>
      <ul style={{ marginTop: 16 }}>
        {habits.map((h) => (
          <li key={h.id} style={{ display: "flex", gap: 12, marginBottom: 8 }}>
            <span style={{ flex: 1 }}>{h.name}</span>
            <button onClick={() => mark(h.id)}>Mark done</button>
          </li>
        ))}
      </ul>
    </main>
  );
}
