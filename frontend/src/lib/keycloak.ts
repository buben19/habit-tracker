import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:8888",          // Keycloak base URL (no /auth in recent versions)
  realm: "habit-realm",
  clientId: "habit-frontend",
});

export default keycloak;