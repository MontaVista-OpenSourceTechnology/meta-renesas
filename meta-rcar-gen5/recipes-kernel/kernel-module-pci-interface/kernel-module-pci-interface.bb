DESCRIPTION = "PCI Interface driver for the R-Car Gen5"

require include/rcar-bsp-modules-common.inc
require include/rcar-bsp-path-common.inc

LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://${WORKDIR}/git/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://${WORKDIR}/git/MIT-COPYING;md5=b00fbfbdda19f05fbe4c74fa86ec073b \
"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-pci-interface"
PR = "r0"

RPCI_DRV_URL = "git://github.com/renesas-rcar/rpci_drv.git"
BRANCH = "rcar_gen5.rc1"
SRCREV = "120a64fd6329afad8c18d95f5eac528ecf5f4a33"

SRC_URI = "${RPCI_DRV_URL};branch=${BRANCH};protocol=https"

S = "${WORKDIR}/git/rpci-module"

KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_install () {
    # Create destination directories
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}

    # Install kernel module
    install -m 644 ${S}/rcar-pci-host.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${S}/rcar-pci-epf.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/rcar_pci.h ${KERNELSRC}/include/

    # Install shared header file
    install -m 644 ${S}/rcar_pci.h ${D}/${includedir}/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/rcar-pci-host.ko \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/rcar-pci-epf.ko \
"

FILES:${PN}-dev = " \
    ${includedir}/rcar_pci.h \
"

RPROVIDES:${PN} += "kernel-module-rcar-pci-host kernel-module-rcar-pci-epf"

