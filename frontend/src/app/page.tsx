"use client";

import { useEffect, useState } from "react";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/ui/KeycloakProvider";
import Link from "next/link";
import { Button, Table } from "react-bootstrap";

type Habit = {
  id: number;
  userId: string;
  schedule: string;
  name: string;
  description: string;
}

export default function HomePage() {
  const [habits, setHabits] = useState<Habit[]>([]);
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
  }

  return (
    <main>
      <h1>Today&apos;s habits</h1>
      <div>
        <Table striped bordered hover>
          <thead>
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
              <tr key={habit.id}>
                <td>{habit.userId}</td>
                <td>{habit.schedule}</td>
                <td>{habit.name}</td>
                <td>{habit.description}</td>
                <td>
                  <div className="d-grid gap-2">
                    <Button variant="secondary" size="sm" onClick={ () => mark(habit.id) }><i className="bi bi-check"></i></Button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
      <Link type="button" className="btn btn-primary" href="/habit/new">New</Link>
    </main>
  );
}
