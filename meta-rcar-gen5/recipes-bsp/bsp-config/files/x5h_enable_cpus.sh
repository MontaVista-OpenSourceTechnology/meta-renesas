#!/bin/bash
for i in {1..31};
do
    echo 1 > /sys/devices/system/cpu/cpu$i/online
done

