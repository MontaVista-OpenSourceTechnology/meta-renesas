DESCRIPTION = "Linux kernel for the R-Car X5x based boards"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "x5h|ironhide"

SRCREV = "${RENESAS_BSP_SRCREV}"
SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH} \
    file://mvq32xx.ko \
    file://ar0820.ko \
    file://imx728.ko \
    file://max96712.ko \
"

LINUX_VERSION ?= "6.1.102"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

PACKAGES += "${PN}-uapi"

do_install:append:rcar-gen5() {
    # Install R-Car specific UAPI headers
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/

    # Install dmatest module
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    mv ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

do_install:append:r8a78000() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${WORKDIR}/mvq32xx.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${WORKDIR}/ar0820.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${WORKDIR}/imx728.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${WORKDIR}/max96712.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

# Deploy vmlinux to deploy directory
do_deploy:append:rcar-gen5() {
    install -m 0644 ${KERNEL_OUTPUT_DIR}/vmlinux $deployDir/
}

FILES:${PN}-uapi = "/usr/include"

# uio_pdrv_genirq and dmatest configuration
KERNEL_MODULE_AUTOLOAD:append = " uio_pdrv_genirq dmatest"
KERNEL_MODULE_PROBECONF:append = " uio_pdrv_genirq dmatest i3c-rcar-master at24"
module_conf_uio_pdrv_genirq:append = ' options uio_pdrv_genirq of_id="generic-uio"'
module_conf_i3c-rcar-master = "blacklist i3c-rcar-master"
module_conf_at24 = "blacklist at24"

