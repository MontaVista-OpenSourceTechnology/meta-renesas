DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

S = "${WORKDIR}/git"

BRANCH = "rcar-s4_v2.11"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH};protocol=https"
SRCREV = "fdeaf1c00dd55b1151897d202441e5fa8797d65c"

PV = "v2.11+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "(spider|s4sk)"
PLATFORM = "rcar_gen4"
ATFW_OPT:r8a779f0 = "LSI=S4 CTX_INCLUDE_AARCH32_REGS=0 LOG_LEVEL=10 DEBUG=0"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake distclean
    oe_runmake bl31 rcar_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${ATFW_OPT} MARCH_DIRECTIVE="-march=armv8.2-a+crypto"
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/bl31-${MACHINE}.srec
}

addtask deploy before do_build after do_compile
