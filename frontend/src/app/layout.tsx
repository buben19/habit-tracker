import "./globals.css";
import { KeycloakProvider } from "@/ui/KeycloakProvider";
import { NavBar } from "@/ui/NavBar";
import { Container } from "react-bootstrap";

export const metadata = {
  title: 'Habit Tracker',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <KeycloakProvider>
          <NavBar />
          <Container>
            {children}
          </Container>
        </KeycloakProvider>
      </body>
    </html>
  );
}
