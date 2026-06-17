DESCRIPTION = "OP-TEE OS"

LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=c1f21c4f72f372ef38a5a4aee55ec173 \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy python3native

PV = "4.3.0+renesas+git${SRCPV}"

BRANCH = "rcar-gen4_4.3.0_s4+v4x"
SRCREV = "d7ff42fa05abaecb8edefad30e0df025d4566eaf"

SRC_URI = " \
    git://github.com/renesas-rcar/optee_os.git;branch=${BRANCH};protocol=https \
"

COMPATIBLE_MACHINE = "(spider|s4sk)"
PLATFORM = "rcar_gen4"

DEPENDS = "python3-pycryptodome-native python3-cryptography-native python3-pyelftools-native"

export CROSS_COMPILE64="${TARGET_PREFIX}"

# Let the Makefile handle setting up the flags as it is a standalone application
#LD[unexport] = "1"
LDFLAGS[unexport] = "1"
libdir[unexport] = "1"

S = "${WORKDIR}/git"
EXTRA_OEMAKE = "-e MAKEFLAGS="

do_compile() {
    export CRYPTOGRAPHY_OPENSSL_NO_LEGACY=1
    oe_runmake PLATFORM=${PLATFORM} CFG_ARM64_core=y
}

do_install () {
    #install TA devkit
    install -d ${D}/usr/include/optee/export-user_ta/

    for f in  ${B}/out/arm-plat-${PLATFORM}/export-ta_arm64/* ; do
        cp -aR  $f  ${D}/usr/include/optee/export-user_ta/
    done
}

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    # Copy TEE OS to deploy folder
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.elf ${DEPLOYDIR}/tee-${MACHINE}.elf
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee-raw.bin ${DEPLOYDIR}/tee-${MACHINE}.bin
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.srec ${DEPLOYDIR}/tee-${MACHINE}.srec
}

addtask deploy before do_build after do_compile


FILES:${PN}-staticdev += " \
    /usr/include/optee/export-user_ta/lib/*.a \
"

FILES:${PN}-dev:remove = " \
    /usr/include/optee/export-user_ta/lib/*.a \
"

INSANE_SKIP:${PN}-dev += "buildpaths"

