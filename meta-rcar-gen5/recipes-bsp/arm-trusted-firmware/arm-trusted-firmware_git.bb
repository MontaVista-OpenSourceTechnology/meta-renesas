DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy


BRANCH:rcar-gen5-vpf = "rcar_gen5_2.12.0_vdk"
BRANCH:rcar-gen5-evb = "rcar_gen5_2.12.0"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH};protocol=https"

SRCREV:rcar-gen5-vpf = "d241e3cedf24854b43a9b212e5e203d84e406de9"
SRCREV:rcar-gen5-evb = "c61d3d3e403ebb8993a603a3173dd1b368d78973"

PV = "v2.5+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "x5h_vpf|ironhide"
PLATFORM = "rcar_gen5"
ATFW_OPT:rcar-gen5-vpf = "LSI=X5H CTX_INCLUDE_AARCH32_REGS=0 SPD=none LOG_LEVEL=40 DEBUG=0 SET_SCMI_PARAM=1 VDK_ENV=1"
ATFW_OPT:rcar-gen5-evb = "LSI=X5H CTX_INCLUDE_AARCH32_REGS=0 LOG_LEVEL=20 DEBUG=0 SET_SCMI_PARAM=1"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE = "${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake distclean
    oe_runmake clean_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${ATFW_OPT}
    oe_runmake bl31 rcar_srecord PLAT=${PLATFORM} SPD=opteed MBEDTLS_COMMON_MK=1 ${ATFW_OPT}
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/apu-bl31-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/apu-bl31-${MACHINE}.srec
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOYDIR}/apu-bl31-${MACHINE}.elf
}

addtask deploy before do_build after do_compile
