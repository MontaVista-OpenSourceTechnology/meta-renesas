DESCRIPTION = "PCI Interface library for R-Car"

require pci-interface.inc

DEPENDS = "kernel-module-pci-interface"
PN = "pci-interface-lib"
PR = "r0"

S = "${UNPACKDIR}/git/rpci_if-module"

do_install() {
    # Create destination directories
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}

    # Copy shared library
    install -m 755 ${S}/librpci.so* ${D}/${libdir}/

    # Install shared header file
    install -m 644 ${S}/rcar_pci_api.h ${D}/${includedir}/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${libdir}/librpci.so* \
"

FILES:${PN}-dev = " \
    ${includedir}/rcar_pci_api.h \
"

