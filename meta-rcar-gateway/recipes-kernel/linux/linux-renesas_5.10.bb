DESCRIPTION = "Linux kernel for the R-Car Gateway based board"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "spider|s4sk"

RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git"
BRANCH = "v5.10.235/rcar-5.1.10.rc1"
SRCREV = "3b9a5e5efc1da1705076b66a6b0290f5c3580fee"

SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${BRANCH};protocol=https \
    file://0001-arm64-dts-renesas-r8a779f0-Add-Native-device-support.patch \
    file://0002-arm64-dts-renesas-r8a779f0-Enable-IPMMU-main-and-HC-.patch \
    file://0003-arm64-dts-renesas-r8a779f0-Enable-IPMMU-for-PCIe0-1.patch \
    file://0004-arm64-dts-renesas-r8a779f0-Enable-IPMMU-for-eMMC.patch \
    file://init_disassemble_info-signature-changes-causes-compile-failures.patch \
    file://ufs.cfg \
    file://r8a779f0_ufs.bin \
"

LINUX_VERSION ?= "5.10.235"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
# Use custom defconfig (r8a779f0_defconfig) when USE_OPTIMIZE_KCONFIG is set
# Falls back to default 'defconfig' otherwise
KCONFIG_MODE = "${@oe.utils.conditional('USE_OPTIMIZE_KCONFIG', '1', 'alldefconfig', '--alldefconfig', d)}"
KBUILD_DEFCONFIG = "${@oe.utils.conditional('USE_OPTIMIZE_KCONFIG', '1', '', 'defconfig', d)}"
KBUILD_DEFCONFIG_KMACHINE ?= "${@oe.utils.conditional('USE_OPTIMIZE_KCONFIG', '1', 'r8a779f0_defconfig', 'defconfig', d)}"

SUPPORT_OPTIMIZE_KCONFIG = " \
    file://r8a779f0_defconfig \
    file://0005-soc-renesas-rcar-sysc-Add-r8a779f0-support.patch \
"

SRC_URI:append = " \
    ${@oe.utils.conditional('USE_OPTIMIZE_KCONFIG', '1', '${SUPPORT_OPTIMIZE_KCONFIG}', '', d)} \
"

# uio_pdrv_genirq configuration
KERNEL_MODULE_AUTOLOAD:append = " uio_pdrv_genirq"
KERNEL_MODULE_PROBECONF:append = " uio_pdrv_genirq"
module_conf_uio_pdrv_genirq:append = ' options uio_pdrv_genirq of_id="generic-uio"'

PACKAGES += "${PN}-uapi"

do_copy_defconfig() {
    if [ "${USE_OPTIMIZE_KCONFIG}" = "1" ] && [ -f "${WORKDIR}/r8a779f0_defconfig" ]; then
        install -d ${S}/arch/arm64/configs
        cp ${WORKDIR}/r8a779f0_defconfig ${S}/arch/arm64/configs/r8a779f0_defconfig
    fi
}
addtask do_copy_defconfig after do_validate_branches before do_kernel_metadata

do_download_firmware () {
    install -d ${STAGING_KERNEL_DIR}/firmware
    install -m 755 ${WORKDIR}/r8a779f0_ufs.bin ${STAGING_KERNEL_DIR}/firmware/
}

addtask do_download_firmware after do_configure before do_compile

do_src_package_preprocess () {
        # Trim build paths from comments in generated sources to ensure reproducibility
        sed -i -e "s,${S}/,,g" \
               -e "s,${B}/,,g" \
            ${B}/drivers/tty/vt/consolemap_deftbl.c \
            ${B}/lib/oid_registry_data.c
}
addtask do_src_package_preprocess after do_compile before do_install

# Install S4 specific UAPI headers and ufs firmware
do_install:append() {
    install -d ${D}/usr/include/linux/
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${D}${nonarch_base_libdir}/firmware/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/
    mv ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${S}/firmware/r8a779f0_ufs.bin ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN}-uapi = "/usr/include"
# dmatest autoload configuration
KERNEL_MODULE_AUTOLOAD:append = " dmatest"
KERNEL_MODULE_PROBECONF:append = " dmatest"
