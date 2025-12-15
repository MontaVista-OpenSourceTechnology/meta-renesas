#!/bin/bash

echo "Stop TAUJ3"
devmem2 0xC1391058 b 0x01

echo -e "\nEnable CLK_LSIOSC for TAUJ"
devmem2 0xC13201F0 w 0xA5A5A501
devmem2 0xC1320120 w 0x00000000
devmem2 0xC13201F0 w 0xA5A5A500

echo -e "\nWait for clock source change"
while [ "$(devmem2 0xC1320128 | awk '/Read at address/{print $NF}')" != "0x00000000" ]; do
    sleep 0.1
done

echo -e "\nSet TAUJ3CDR0"
#devmem2 0xC1391000 w 0x00FFFFFF
devmem2 0xC1391000 w 0x003FFFFF

echo -e "\nSet TAUJ3 timer mode"
devmem2 0xC1391090 h 0x0000
devmem2 0xC1391080 h 0x0000
devmem2 0xC1391020 b 0x00
devmem2 0xC1391060 b 0x01
devmem2 0xC1391098 b 0x00
devmem2 0xC139109C b 0x00
devmem2 0xC1391064 b 0x00

echo -e "\nStart TAUJ3"
devmem2 0xC1391054 b 0x01

#echo -e "\nsleep 3s\n"
#sleep 3
echo N > /sys/module/printk/parameters/console_suspend
echo 7 > /proc/sys/kernel/printk
echo -e "\nStart deepstop (echo mem > /sys/power/state)"
echo mem > /sys/power/state