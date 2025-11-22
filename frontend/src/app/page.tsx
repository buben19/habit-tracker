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

type Checkin = {
  id: number;
  userId: string;
  habitId: number;
  date: string
}

type HabitWithCheckins = {
  habit: Habit;
  checkin: Checkin[];
};

type HabitResponse = {
  habits: HabitWithCheckins[];
}

export default function HomePage() {
  const [habitResponse, setHabitResponse] = useState<HabitResponse>({ habits: [] });
  const { authenticated, token } = useAuth();

  useEffect(() => {
    if (authenticated) {
      apiFetch("/habits/with-checkins", token)
        .then(setHabitResponse)
        .catch((e) => console.error(e));
    }
  }, [ authenticated, token ]);

  async function mark(habitId: number) {
    const checkinResponse = await apiFetch(`/checkins/today/${habitId}`, token, {
      method: "POST",
    });
    console.log("Checkin response: ", JSON.stringify(checkinResponse));
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
            <th>Checkins</th>
            <th>Checkin</th>
          </tr>
          </thead>
          <tbody>
            {habitResponse.habits.map(habit => (
              <tr key={habit.habit.id}>
                <td>{habit.habit.userId}</td>
                <td>{habit.habit.schedule}</td>
                <td>{habit.habit.name}</td>
                <td>{habit.habit.description}</td>
                <td>{habit.checkin?.length}</td>
                <td>
                  <div className="d-grid gap-2">
                    <Button variant="secondary" size="sm" onClick={ () => mark(habit.habit.id) }><i className="bi bi-check"></i></Button>
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
