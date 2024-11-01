DESCRIPTION = "PCI Interface library for R-Car"

require pci-interface.inc

DEPENDS = "kernel-module-pci-interface"
PN = "pci-interface-lib"
PR = "r0"

S = "${WORKDIR}/rpci_lib/rpci_if-module"

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

FILES_${PN} = " \
    ${libdir}/librpci.so* \
"

FILES_${PN}-dev = " \
    ${includedir}/rcar_pci_api.h \
"
