import { useAuth } from "@/app/ui/KeycloakProvider";

export async function apiFetch(path: string, token?: string, init: RequestInit = {}) {
  const headers: any = {
    "Content-Type": "application/json",
    ...(init.headers || {})
  };
  if (token) {
    headers["Authorization"] = `Bearer ${token}`;
  }

  const res = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}${path}`, {
    ...init,
    headers,
    cache: "no-store"
  });
  if (!res.ok) {
    throw new Error(await res.text());
  }
  return res.json();
}
