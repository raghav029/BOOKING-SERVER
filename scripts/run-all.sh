#!/usr/bin/env bash
set -euo pipefail

# Recommended port mapping (adjust if you prefer different ports)
./scripts/run-service.sh auth-service     8081 &
./scripts/run-service.sh user-service     8082 &
./scripts/run-service.sh car-service      8083 &
./scripts/run-service.sh booking-service  8084 &
./scripts/run-service.sh payment-service  8085 &
./scripts/run-service.sh support-service  8086 &
# API Gateway should run last (depends on other modules in this repo)
./scripts/run-service.sh api-gateway     8080 &

wait

echo "All start commands dispatched. Check logs/ and tmp/ for pids."