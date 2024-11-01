LICENSE = "CLOSED"

require include/rcar-bsp-modules-common.inc

SRC_URI = "file://rpci_lib.tar.bz2"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"
