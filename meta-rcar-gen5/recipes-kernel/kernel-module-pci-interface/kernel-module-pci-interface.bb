DESCRIPTION = "PCI Interface driver for the R-Car Gen5"

require include/rcar-bsp-modules-common.inc
require include/rcar-bsp-path-common.inc

LICENSE = "CLOSED"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-pci-interface"
PR = "r0"

SRC_URI = "file://rpci_drv.tar.bz2"

S = "${WORKDIR}/rpci_drv/rpci-module"

do_install () {
    # Create destination directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}

    # Install kernel module
    install -m 644 ${S}/rcar-pci-host.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${S}/rcar-pci-epf.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${S}/rcar_pci.h ${KERNELSRC}/include/

    # Install shared header file
    install -m 644 ${S}/rcar_pci.h ${D}/${includedir}/
}