"use client";

import { createContext, useEffect, useState } from "react";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/app/ui/KeycloakProvider";

export default function HomePage() {
  const [habits, setHabits] = useState<any[]>([]);
  const { initialized, authenticated, login, logout, token } = useAuth();

  useEffect(() => {
    if (authenticated) {
      apiFetch("/habits", token)
        .then(setHabits)
        .catch((e) => console.error(e));
    }
  }, [ authenticated, token ]);

  async function mark(habitId: number) {
    await apiFetch("/checkins", token, {
      method: "POST",
      body: JSON.stringify({ habitId })
    });
    alert("Marked!");
  }

  return (
    <main>
      <h1>Today&apos;s habits</h1>
      <div className="relative bg-neutral-primary-soft shadow-xs">
        <table className="table">
          <thead className="bg-neutral-secondary-soft">
          <tr>
            <th>Habit ID</th>
            <th>Schedule</th>
            <th>Name</th>
            <th>Description</th>
            <th>Checkin</th>
          </tr>
          </thead>
          <tbody>
          {habits.map(habit => (
            <tr key={habit.id} className="hover:bg-base-300">
              <td style={{ flex: 1 }}>{habit.userId}</td>
              <td style={{ flex: 1 }}>{habit.schedule}</td>
              <td style={{ flex: 1 }}>{habit.name}</td>
              <td style={{ flex: 1 }}>{habit.description}</td>
              <td>
                <button className="btn btn-neutral" onClick={() => mark(habit.id)}>Mark done</button>
              </td>
            </tr>
          ))}
          </tbody>
        </table>
      </div>
    </main>
  );
}
