# Habit tracker 🚀

- 🌐 http://localhost:3000/ - Habit tracker React frontend
- 🧩 http://localhost:8080/ - Habit tracker API service
- 🧭 http://localhost:8761/ - Eureka server
- 🔐 http://localhost:8888/ - Keycloak server
- 🔎 http://localhost:9411/ - Zipkin server
- 📊 http://localhost:3001/ - Grafana server
- 📈 http://localhost:9090/ - Prometheus server
- 📚 http://localhost:5540/ - RedisInsight
- 👂 http://localhost:8090/ - Kafka UI

## Description ✨
Habit Tracker is a microservices-based application for recording and visualizing personal habits over time. It uses a React frontend and a Java Spring Boot backend (Spring Cloud ecosystem) and integrates infrastructure services for service discovery, tracing, monitoring and authentication.

## Technology stack 🧰
- Frontend: React using Next.js ⚛️
- Backend: Java + Spring Boot, Spring Cloud (Eureka, Gateway) ☕
- Auth: Keycloak 🔐
- Tracing: Zipkin 🔎
- Monitoring: Prometheus + Grafana 📈📊
- Service discovery: Eureka 🧭
- Optional: Docker / docker-compose for local infra 🐳

## Prerequisites ⚙️
- Node.js + npm or yarn (for frontend)
- Java 21 (for backend)
- Maven (backend build)
- Docker & docker-compose (recommended for local infra)

## Quick start (local development) ▶️
1. Start infrastructure services (recommended):
   - Using docker-compose: `docker-compose -f docker-compose.yml up -d` 🐳
2. Start backend API:
   - From each backend service folder: `./mvnw spring-boot:run` or `mvn spring-boot:run` ☕
   - **Note:** Start Config Server, then Discovery, then Gateway, then other services.
3. Start frontend:
   - From the frontend folder: `npm install` then `npm start` ⚛️
4. Wait a few seconds for services to initialize and register with Eureka. Check Eureka dashboard at http://localhost:8761/ to see registered services. 🧭
5. Now you can log in into the app at http://localhost:3000/:
   - Default user: `buben` / `buben` 🔐
5. Open the app and tools:
   - Frontend: http://localhost:3000/ 🌐
   - API: http://localhost:8080/ 🧩
   - Eureka: http://localhost:8761/ 🧭
   - Keycloak: http://localhost:8888/ 🔐
   - Zipkin: http://localhost:9411/ 🔎
   - Prometheus: http://localhost:9090/ 📈
   - Grafana: http://localhost:3001/ 📊

## Project structure 📁
- Business logic
  - `config-server/`     — Spring Cloud Config server
  - `discovery/`         — Eureka service registry
  - `gateway/`           — API Gateway
  - `habit-service/`     — Habit tracking service
  - `checkin-service/`   — Check-in service
  - `frontend/`          — React app
- Configuration
  - `docker-compose.yml` — Docker Compose file for local infra
  - `keycloak/`          — Keycloak configuration and realm export
  - `prometheus/`        — Prometheus configuration
  - `init-db/`           — SQL scripts for initializing databases
  - `kui`                — Kafka UI configuration
- Other
  - `http/`              — HTTP client library for backend services

## Contributing 🤝
- Open issues for bugs or features.
- Send a PR with a clear description and tests where appropriate.
