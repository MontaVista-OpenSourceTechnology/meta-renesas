LICENSE = "CLOSED"

require include/rcar-bsp-modules-common.inc

LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = "file://MIT-COPYING;md5=b00fbfbdda19f05fbe4c74fa86ec073b"

RPCI_LIB_URL = "git://github.com/renesas-rcar/rpci_lib.git"
BRANCH = "rcar_gen5.rc1"
SRCREV = "3f02f56498a3f18b95d203962f70c6fd9d758fe1"

SRC_URI = "${RPCI_LIB_URL};branch=${BRANCH};protocol=https"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"
