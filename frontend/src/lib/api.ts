export async function apiFetch(path: string, init: RequestInit = {}) {
  const res = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}${path}`, {
    ...init,
    headers: {
      "Content-Type": "application/json",
      ...(init.headers || {})
    },
    cache: "no-store"
  });
  if (!res.ok) {
    throw new Error(await res.text());
  }
  return res.json();
}
