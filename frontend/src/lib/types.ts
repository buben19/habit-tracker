export type Habit = {
  id: number;
  userId: string;
  userName: string;
  schedule: string;
  name: string;
  description: string | null;
}

export type Checkin = {
  id: number;
  userId: string;
  habitId: number;
  date: string
}

export type HabitWithCheckins = {
  habit: Habit;
  checkin: Checkin[] | null;
};

export type HabitResponse = {
  habits: HabitWithCheckins[];
}