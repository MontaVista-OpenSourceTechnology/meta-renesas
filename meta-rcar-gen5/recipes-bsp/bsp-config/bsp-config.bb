SUMMARY = "BSP configuration (default/ADAS) script"
LICENSE = "CLOSED"

S = "${UNPACKDIR}"

SRC_URI = " \
    file://bsp-config_v4h.sh \
    file://x5h_enable_cpus.sh \
    file://s2r-linux-script-removeSleep3s-increaseTaujValue.sh \
    file://tsn4_steering.sh \
    file://ufs-config-x5h.bin \
    file://ufs-config-mdp_sandisk.bin \
    file://ufs-config-mdp_micron.bin \
    file://pcitest_dma_script.sh \
"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/bsp-config_v4h.sh ${D}${bindir}/
    install -m 755 ${S}/x5h_enable_cpus.sh ${D}${bindir}/
    install -m 755 ${S}/s2r-linux-script-removeSleep3s-increaseTaujValue.sh ${D}${bindir}/

    install -d ${D}${ROOT_HOME}
    install -m 755 ${S}/tsn4_steering.sh ${D}${ROOT_HOME}/
    install -m 755 ${S}/ufs-config-x5h.bin ${D}${ROOT_HOME}/
    install -m 755 ${S}/ufs-config-mdp_sandisk.bin ${D}${ROOT_HOME}/
    install -m 755 ${S}/ufs-config-mdp_micron.bin ${D}${ROOT_HOME}/
    install -m 755 ${S}/pcitest_dma_script.sh ${D}${ROOT_HOME}/
}

RDEPENDS:${PN} += "bash"

FILES:${PN} = "\
    ${ROOT_HOME}/* \
    ${bindir}/* \
"
