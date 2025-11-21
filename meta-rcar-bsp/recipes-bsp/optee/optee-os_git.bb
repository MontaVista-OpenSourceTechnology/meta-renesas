DESCRIPTION = "OP-TEE OS"

LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=c1f21c4f72f372ef38a5a4aee55ec173 \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy python3native

PV = "4.3.0+renesas+git${SRCPV}"

BRANCH = "rcar-gen4_4.3.0_s4+v4x"
SRCREV = "f5580809207828fbbe265859d153feab52243618"

SRC_URI = " \
    git://github.com/renesas-rcar/optee_os.git;branch=${BRANCH};protocol=https \
    file://0001-ta-pkcs11-empty-attributes-should-not-be-loaded-into.patch \
    file://0002-ta-pkcs11-be-flexible-on-RSA-private-key-optional-at.patch \
    file://0003-plat-rcar-ss_provider-Use-optional-fields-of-RSA-key.patch \
    file://0004-plat-rcar-ss_provider-temp-Skip-checking-condition-t.patch \
    file://0005-plat-rcar_gen4-Implement-additional-ICUMFW-security-.patch \
    file://0006-plat-rcar_gen4-Implement-rcar_install_user_key-for-n.patch \
    file://0007-Implement-new-TEE-internal-API-and-system-call-to-in.patch \
    file://0008-plat-rcar_gen4-Add-suport-external-flash-API-to-stor.patch \
    file://0009-Implement-new-TEE-internal-API-and-system-call-to-in.patch \
    file://0010-plat-rcar_gen4-Implement-rcar_asset_unpack-for-the-n.patch \
    file://0011-Update-RPMB-using-ICUM-security-services.patch \
    file://0012-Fix-build-warnings.patch \
"

COMPATIBLE_MACHINE = "(whitehawk|grayhawk)"
PLATFORM = "rcar_gen4"

DEPENDS = "python3-cryptography-native python3-pyelftools-native"

export CROSS_COMPILE64="${TARGET_PREFIX}"

# Let the Makefile handle setting up the flags as it is a standalone application
#LD[unexport] = "1"
LDFLAGS[unexport] = "1"
libdir[unexport] = "1"

S = "${WORKDIR}/git"
EXTRA_OEMAKE = "-e MAKEFLAGS="

SOC:r8a779g0 = "V4H"
SOC:r8a779h0 = "V4M"

do_compile() {
    export CRYPTOGRAPHY_OPENSSL_NO_LEGACY=1

    if [ "${RGID_ON}" = "1" ]; then
        oe_runmake PLATFORM=${PLATFORM} LSI=${SOC} CFG_ARM64_core=y CFG_RCAR_RGID_ENABLE=y
    else
        oe_runmake PLATFORM=${PLATFORM} LSI=${SOC} CFG_ARM64_core=y
    fi
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    # Copy TEE OS to deploy folder
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.elf ${DEPLOYDIR}/tee-${MACHINE}.elf
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee-raw.bin ${DEPLOYDIR}/tee-${MACHINE}.bin
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.srec ${DEPLOYDIR}/tee-${MACHINE}.srec
}

addtask deploy before do_build after do_compile
