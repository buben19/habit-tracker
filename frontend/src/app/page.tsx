"use client";

import { createContext, useEffect, useState } from "react";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/app/ui/KeycloakProvider";

export default function HomePage() {
  const [habits, setHabits] = useState<any[]>([]);
  const { initialized, authenticated, login, logout, token } = useAuth();

  useEffect(() => {
    console.log("Fetching habits with token: ", token);
    apiFetch("/habits", token)
      .then(setHabits)
      .catch((e) => console.error(e));
  }, [ initialized, authenticated, token ]);

  if (!initialized) {
    return <div>Loading auth…</div>;
  }

  if (!authenticated) {
    return (
      <div>
        <p>Not logged in.</p>
        <button onClick={login}>Login with Keycloak</button>
      </div>
    );
  }

  async function mark(habitId: number) {
    await apiFetch("/checkins", token, {
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
            <span style={{ flex: 1 }}>{h.schedule}</span>
            <span style={{ flex: 1 }}>{h.name}</span>
            <span style={{ flex: 1 }}>{h.description}</span>
            <button onClick={() => mark(h.id)}>Mark done</button>
          </li>
        ))}
      </ul>
    </main>
  );
}
