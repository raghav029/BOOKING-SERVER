#!/usr/bin/env bash
set -euo pipefail

if [ "$#" -lt 2 ]; then
  echo "Usage: $0 <module> <port> [profile]"
  exit 2
fi
MODULE="$1"
PORT="$2"
PROFILE="${3:-local}"
# allow overriding DB connection via env vars, otherwise use local defaults
DB_URL="${DB_URL:-jdbc:postgresql://localhost:5432/luxury_car_db}"
DB_USER="${DB_USER:-raghavjha}"
DB_PASS="${DB_PASS:-}"
LOGDIR="$(pwd)/logs"
PIDDIR="$(pwd)/tmp"
mkdir -p "$LOGDIR" "$PIDDIR"

echo "Starting $MODULE on port $PORT (profile=$PROFILE)..."
# Use module pom directly to avoid running the parent aggregator which can
# cause "Unable to find a suitable main class" when spring-boot plugin picks
# the aggregator instead of the module.
nohup mvn -f "$MODULE/pom.xml" -am spring-boot:run -DskipTests -Dspring-boot.run.arguments="--server.port=${PORT} --spring.profiles.active=${PROFILE} --spring.datasource.url=${DB_URL} --spring.datasource.username=${DB_USER} --spring.datasource.password=${DB_PASS}" > "$LOGDIR/${MODULE}.log" 2>&1 &
PID=$!
echo $PID > "$PIDDIR/${MODULE}.pid"
sleep 1
echo "$MODULE started (pid=$PID)  log=$LOGDIR/${MODULE}.log"
