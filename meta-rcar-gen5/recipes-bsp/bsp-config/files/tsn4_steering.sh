#!/bin/bash

# Assign IRQs to specific CPU cores using bitmasks to distribute interrupt handling.
PATTERN="${1:-gwca1_gwdis}"

grep -E "$PATTERN[0-9]+$" /proc/interrupts | while read -r line; do
	irq=$(printf "%s\n" "$line" | awk '{gsub(":","",$1); print $1}')
	name=$(printf "%s\n" "$line" | awk '{print $NF}')
	n=${name#"$PATTERN"}
	case "$n" in
		0) m=2 ;;
		1) m=4 ;;
		2) m=8 ;;
		3) m=10 ;;
		4) m=20 ;;
		5) m=40 ;;
		6) m=80 ;;
		7) m=100 ;;
		*) continue ;;
	esac
	echo "$m" > "/proc/irq/$irq/smp_affinity" 2>/dev/null || true
done

# Set XPS (Transmit Packet Steering) to control which CPUs handle packet transmission for each TX queue. 
echo 200 > /sys/class/net/tsn4/queues/tx-0/xps_cpus
echo 400 > /sys/class/net/tsn4/queues/tx-1/xps_cpus
echo 800 > /sys/class/net/tsn4/queues/tx-2/xps_cpus
echo 1000 > /sys/class/net/tsn4/queues/tx-3/xps_cpus

# Set RPS (Receive Packet Steering) to distribute packet reception across CPUs for RX queues.
echo 2000 > /sys/class/net/tsn4/queues/rx-0/rps_cpus
echo 4000 > /sys/class/net/tsn4/queues/rx-1/rps_cpus
echo 8000 > /sys/class/net/tsn4/queues/rx-2/rps_cpus
echo 10000 > /sys/class/net/tsn4/queues/rx-3/rps_cpus
