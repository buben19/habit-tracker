"use client";

import { useEffect, useState } from "react";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/app/ui/KeycloakProvider";
import Link from "next/link";

export default function HomePage() {
  const [habits, setHabits] = useState<any[]>([]);
  const { authenticated, token } = useAuth();

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
      <h1 className="flex-1 text-xl">Today&apos;s habits</h1>
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
                <td>{habit.userId}</td>
                <td>{habit.schedule}</td>
                <td>{habit.name}</td>
                <td>{habit.description}</td>
                <td>
                  <button className="btn btn-neutral btn-xs" onClick={() => mark(habit.id)}>Mark done</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Link className="btn btn-neutral text-xl" href="/habit/new">New</Link>
    </main>
  );
}
