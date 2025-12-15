SUMMARY = "BSP configuration (default/ADAS) script"
LICENSE = "CLOSED"

S = "${WORKDIR}"

SRC_URI = " \
    file://bsp-config_v4h.sh \
    file://x5h_enable_cpus.sh \
    file://s2r-linux-script-removeSleep3s-increaseTaujValue.sh \
"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/*.sh ${D}${bindir}/
}

RDEPENDS:${PN} += "bash"
