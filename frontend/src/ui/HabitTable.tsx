import { Button, Table } from "react-bootstrap";
import { HabitResponse } from "@/lib/types";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/ui/KeycloakProvider";
import { useEffect, useState } from "react";

export default function HabitTable() {
  const [ habitResponse, setHabitResponse ] = useState<HabitResponse>({ habits: [] });
  const { authenticated, token } = useAuth();

  async function fetchHabits() {
    if (authenticated) {
      return apiFetch("/habits/with-checkins", token)
        .then(setHabitResponse)
        .catch((e) => console.error(e));
    }
  }

  useEffect(() => {
    fetchHabits();
  }, [ authenticated, token ]);

  async function mark(habitId: number) {
    const checkinResponse = await apiFetch(`/checkins/today/${habitId}`, token, {
      method: "POST",
    });
    fetchHabits();
    console.log("Checkin response: ", JSON.stringify(checkinResponse));
  }

  return (
    <Table striped bordered hover>
      <thead>
      <tr>
        <th>User</th>
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
          <td>{habit.habit.userName}</td>
          <td><span className="badge bg-primary">{habit.habit.schedule}</span></td>
          <td>{habit.habit.name}</td>
          <td>{habit.habit.description}</td>
          <td>{habit.checkin?.length}</td>
          <td>
            <div className="d-inline-flex gap-2">
              <Button variant="success" size="sm" onClick={ () => mark(habit.habit.id) }><i className="bi bi-check-lg"></i></Button>
              <Button variant="secondary" size="sm" onClick={ () => mark(habit.habit.id) }><i className="bi bi-pencil-fill"></i></Button>
              <Button variant="danger" size="sm" onClick={ () => mark(habit.habit.id) }><i className="bi bi-trash-fill"></i></Button>
            </div>
          </td>
        </tr>
      ))}
      </tbody>
    </Table>
  );
}