"use client";

import { useEffect, useState } from "react";
import { apiFetch } from "@/lib/api";
import { useAuth } from "@/ui/KeycloakProvider";
import Link from "next/link";
import { HabitResponse } from "@/lib/types";
import HabitTable from "@/ui/HabitTable";

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

  return (
    <main>
      <h1>Today&apos;s habits</h1>
      <div>
        <HabitTable habitResponse={habitResponse} token={token}/>
      </div>
      <Link type="button" className="btn btn-primary" href="/habit/new">New</Link>
    </main>
  );
}
