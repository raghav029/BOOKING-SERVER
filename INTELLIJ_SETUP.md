# Running Microservices in IntelliJ

## Using IntelliJ Run Configurations

The project includes pre-configured Maven run configurations for all microservices. You can find them in the Run Configuration dropdown at the top of the IDE.

### Available Run Configurations:

1. **API Gateway** (Port 8080) - Main entry point with Swagger UI
2. **Auth Service** (Port 8081) - Authentication and JWT token management
3. **User Service** (Port 8082) - User profile and management
4. **Car Service** (Port 8083) - Car inventory and details
5. **Booking Service** (Port 8084) - Booking management
6. **Payment Service** (Port 8085) - Payment processing
7. **Support Service** (Port 8086) - Support tickets and management

### How to Run:

#### Run Individual Service:
1. Open the **Run Configuration** dropdown (top-right of IDE)
2. Select the service you want to run (e.g., "Auth Service")
3. Click the **Run** button (▶️) or press `Ctrl+R`

#### Run Multiple Services:
1. Open **Run** → **Edit Configurations**
2. Create a new **Compound** configuration
3. Add all 7 services in the order you want them to start
4. Run the compound configuration

#### Using Terminal Commands:

Start all services:
```bash
./scripts/run-all.sh
```

Stop all services:
```bash
./scripts/stop-all.sh
```

Start individual service:
```bash
mvn -f auth-service/pom.xml spring-boot:run
```

## Accessing Services

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Auth Service**: http://localhost:8081
- **User Service**: http://localhost:8082
- **Car Service**: http://localhost:8083
- **Booking Service**: http://localhost:8084
- **Payment Service**: http://localhost:8085
- **Support Service**: http://localhost:8086

## Database Configuration

All services use PostgreSQL database: `luxury_car_db`

**Connection Details:**
- Host: `localhost`
- Port: `5432`
- Database: `luxury_car_db`
- Username: `raghavjha`
- Password: (empty - local development)

Connect via DBeaver or any SQL client using these credentials.

## Debugging

To debug a service:
1. Right-click on the run configuration
2. Select **Debug** (or press `Ctrl+D`)
3. Set breakpoints in the code
4. The debugger will stop at breakpoints when requests hit those lines

## Common Issues

### Port Already in Use
If a port is already in use, the service will fail to start. Use:
```bash
lsof -i :8081  # Check which process is using port 8081
kill -9 <PID>  # Kill the process
```

### Database Connection Issues
Ensure PostgreSQL is running:
```bash
# Check if PostgreSQL is running on macOS
brew services list | grep postgres
```

### Maven Build Issues
Clear Maven cache and rebuild:
```bash
mvn clean install -DskipTests
```

## Logs Location

Logs are stored in the `logs/` directory:
- `logs/api-gateway.log`
- `logs/auth-service.log`
- `logs/user-service.log`
- `logs/car-service.log`
- `logs/booking-service.log`
- `logs/payment-service.log`
- `logs/support-service.log`

View logs in real-time:
```bash
tail -f logs/auth-service.log
```
