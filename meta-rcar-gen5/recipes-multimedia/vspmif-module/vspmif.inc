LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://COPYING.MIT;md5=30a99e0d36a3da1f5cf93c070ad7888a \
"

require include/rcar-bsp-modules-common.inc
require include/rcar-bsp-path-common.inc

VSPMIF_LIB_URL = "git://github.com/renesas-rcar/vspmif_lib.git"
BRANCH:x5h = "rcar_gen5_4.20.0"
SRCREV:x5h = "9e87ba88770faee1551163e3b9256dd1f9e2aa5c"

BRANCH:r8a78000 = "rcar_gen5_v4.26.0"
SRCREV:r8a78000 = "35d9beefa4f1de6e5f389727837d0e6f9ba773e2"

SRC_URI = "${VSPMIF_LIB_URL};branch=${BRANCH};protocol=https"

# This module is machine specific.
PACKAGE_ARCH = "${MACHINE_ARCH}"
