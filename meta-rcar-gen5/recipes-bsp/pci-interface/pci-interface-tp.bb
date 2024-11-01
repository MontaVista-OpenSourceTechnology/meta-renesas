DESCRIPTION = "PCI Interface test app for R-Car"

require pci-interface.inc

DEPENDS = "pci-interface-lib"
PN = "pci-interface-tp"
PR = "r0"

S = "${WORKDIR}/rpci_lib/rpci_if-tp-user"

do_compile() {
    cd ${S}
    make all
}

do_install() {
    # Create destination directory
    install -d ${D}${bindir}

    # Copy user test program
    install -m 755 ${S}/rcar_pci_host ${D}${bindir}/
    install -m 755 ${S}/rcar_pci_ep ${D}${bindir}/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dbg \
"

FILES_${PN} = "${bindir}/rcar_pci_host ${bindir}/rcar_pci_ep"

