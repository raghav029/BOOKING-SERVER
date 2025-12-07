#!/usr/bin/env bash
set -euo pipefail
PIDDIR="$(pwd)/tmp"
SERVICES=(auth-service user-service car-service booking-service payment-service support-service api-gateway)

for s in "${SERVICES[@]}"; do
  PID_FILE="$PIDDIR/$s.pid"
  if [ -f "$PID_FILE" ]; then
    pid=$(cat "$PID_FILE")
    echo "Stopping $s (pid=$pid)"
    kill "$pid" || true
    rm -f "$PID_FILE"
  else
    echo "No pid file for $s"
  fi
done

echo "Stop commands issued. You can also check 'ps aux | grep java' to confirm."