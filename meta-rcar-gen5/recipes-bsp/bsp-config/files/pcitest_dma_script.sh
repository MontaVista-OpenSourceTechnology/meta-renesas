#!/bin/sh
# SPDX-License-Identifier: GPL-2.0

echo "Read Tests Without DMA"
echo

for (( i=2 ; i<=16 ; i++ ));
do
	./pcitest -r -s $((2**$i))
done
echo

echo "Write Test Without DMA"
echo

for (( i=2 ; i<=16 ; i++ ));
do
        ./pcitest -w -s $((2**$i))
done
echo

echo "Read Tests With DMA"
echo

for (( i=2 ; i<=16 ; i++ ));
do
        ./pcitest -d -r -s $((2**$i))
done
echo

echo "Write Test With DMA"
echo

for (( i=2 ; i<=16 ; i++ ));
do
        ./pcitest -d -w -s $((2**$i))
done
echo
