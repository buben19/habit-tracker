type AboutLink = {
  url: string;
  description: string;
}

const aboutLinks: AboutLink[] = [
  { url: "http://localhost:3000/", description: "Habit tracker React frontend" },
  { url: "http://localhost:8080/", description: "Habit tracker API service" },
  { url: "http://localhost:8761/", description: "Eureka server" },
  { url: "http://localhost:8888/", description: "Keycloak server" },
  { url: "http://localhost:9411/", description: "Zipkin server" },
  { url: "http://localhost:3001/", description: "Grafana server" },
  { url: "http://localhost:9090/", description: "Prometheus server" },
  { url: "http://localhost:5540/", description: "RedisInsight" },
  { url: "http://localhost:8090/", description: "Kafka UI" },
  { url: "http://localhost:5050/", description: "pgAdmin (PostgreSQL)" },
];

export default function AboutPage() {
  return (
    <main>
      <h1>About Habit Tracker</h1>
      <ul>
        {
          aboutLinks.map(link => (
            <li key={link.url}><a target="_blank" rel="noopener noreferrer" href={link.url}>{link.url}</a> - {link.description}</li>
          ))
        }
      </ul>
    </main>
  )
}
