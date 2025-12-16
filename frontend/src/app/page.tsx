"use client";

import Link from "next/link";
import HabitTable from "@/ui/HabitTable";

export default function HomePage() {
  return (
    <main>
      <h1>Today&apos;s habits</h1>
      <div>
        <HabitTable/>
      </div>
      <Link type="button" className="btn btn-primary" href="/habit/new">New</Link>
    </main>
  );
}
