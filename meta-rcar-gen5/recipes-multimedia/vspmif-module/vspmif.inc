LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=30a99e0d36a3da1f5cf93c070ad7888a \
"

require include/rcar-bsp-modules-common.inc
require include/rcar-bsp-path-common.inc

VSPMIF_LIB_URL = "git://github.com/renesas-rcar/vspmif_lib.git"
BRANCH = "rcar_gen5_4.20.0"
SRCREV = "9e87ba88770faee1551163e3b9256dd1f9e2aa5c"

SRC_URI = "${VSPMIF_LIB_URL};branch=${BRANCH};protocol=https"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"
