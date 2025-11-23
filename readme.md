# Habit tracker 🚀

 - 🧭 http://localhost:8761/ - Eureka server
 - 🧩 http://localhost:8080/ - Habit tracker API service
 - 🌐 http://localhost:3000/ - Habit tracker React frontend
 - 🔐 http://localhost:8888/ - Keycloak server
 - 🔎 http://localhost:9411/ - Zipkin server
 - 📊 http://localhost:3001/ - Grafana server
 - 📈 http://localhost:9090/ - Prometheus server

## Description ✨
Habit Tracker is a microservices-based application for recording and visualizing personal habits over time. It uses a React frontend and a Java Spring Boot backend (Spring Cloud ecosystem) and integrates infrastructure services for service discovery, tracing, monitoring and authentication.

## Technology stack 🧰
- Frontend: React (create-react-app or similar) ⚛️
- Backend: Java + Spring Boot, Spring Cloud (Eureka) ☕
- Auth: Keycloak 🔐
- Tracing: Zipkin 🔎
- Monitoring: Prometheus + Grafana 📈📊
- Service discovery: Eureka 🧭
- Optional: Docker / docker-compose for local infra 🐳

## Prerequisites ⚙️
- Node.js + npm or yarn (for frontend)
- Java 11+ (for backend)
- Maven or Gradle (backend build)
- Docker & docker-compose (recommended for local infra)

## Quick start (local development) ▶️
1. Start infrastructure services (recommended):
   - If a docker-compose is provided: `docker-compose -f infra/docker-compose.yml up -d` 🐳
   - Or start Keycloak, Eureka, Zipkin, Prometheus, Grafana individually as defined in your repo.
2. Start backend API:
   - From the backend service folder: `./mvnw spring-boot:run` or `mvn spring-boot:run` ☕
3. Start frontend:
   - From the frontend folder: `npm install` then `npm start` ⚛️
4. Open the app and tools:
   - Frontend: http://localhost:3000/ 🌐
   - API: http://localhost:8080/ 🧩
   - Eureka: http://localhost:8761/ 🧭
   - Keycloak: http://localhost:8888/ 🔐
   - Zipkin: http://localhost:9411/ 🔎
   - Prometheus: http://localhost:9090/ 📈
   - Grafana: http://localhost:3001/ 📊

## Configuration hints 🛠️
- Backend config: adjust `application.yml` / `application.properties` (service URLs, Keycloak settings, datasource).
- Frontend config: set API base URL via env var (e.g. `REACT_APP_API_URL=http://localhost:8080`) before `npm start` or in a .env file.
- Keycloak: ensure realm, client and roles match backend expectations.

## Project structure (example) 📁
- backend/      — Spring Boot services
- frontend/     — React app
- infra/        — docker-compose and infra manifests
- config/       — central config or Keycloak setup scripts
- docs/         — additional documentation

## Contributing 🤝
- Open issues for bugs or features.
- Send a PR with a clear description and tests where appropriate.

## License 📜
- Add your license here (e.g. MIT) or keep repository-specific license file.
