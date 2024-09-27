DESCRIPTION = "Utility tool of the AVB Streaming Driver for Linux for the R-Car Gen3"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=96659f2a7571bfa27483882a653c3bb9"

require avb-applications.inc

DEPENDS = "kernel-module-avb-streaming"

SRC_URI:append = " file://0003-avbtool-allow-to-append-application-cflags.patch"

S = "${WORKDIR}/git/avbtool"

EXTRA_OEMAKE = "'CC=${CC}'"

# Let the application set CFLAGS itself
TARGET_CFLAGS = "${DEBUG_PREFIX_MAP}"

do_install:append() {
    install -d ${D}/${bindir}
    install -m 755 ${S}/avbtool ${D}/${bindir}
}
