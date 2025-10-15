require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.10.235/rcar-5.1.10.rc1"
SRCREV = "3b9a5e5efc1da1705076b66a6b0290f5c3580fee"

SRC_URI = "${RENESAS_BSP_URL};branch=${BRANCH};protocol=https"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"
