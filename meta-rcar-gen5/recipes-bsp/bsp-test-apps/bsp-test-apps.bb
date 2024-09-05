SUMMARY = "BSP test applications"
LICENSE = "CLOSED"

S = "${WORKDIR}/bsp-test-apps"

SRC_URI = " \
    file://bsp-test-apps.tar.gz \
"

do_install() {
    install -d ${D}${ROOT_HOME}

    # Install watchdog tp
    install -m 755 ${S}/watchdog-test ${D}${ROOT_HOME}/
    install -m 755 ${S}/wwdt_test ${D}${ROOT_HOME}/

    # Install USB tp
    install -m 755 ${S}/gadgetfs ${D}${ROOT_HOME}/

    # Install WCRC tp
    install -m 755 ${S}/sample_wcrc ${D}${ROOT_HOME}/
    install -m 755 ${S}/sample_wcrc_indepent_mode ${D}${ROOT_HOME}/

    # Install I3C tp
    install -m 755 ${S}/i3ctransfer ${D}${ROOT_HOME}/

    # Install Audio test data
    install -d ${D}${ROOT_HOME}/test_wav
    install -d ${D}${ROOT_HOME}/test_wav/normal_sin
    install -m 755 ${S}/test_wav/normal_sin/011025_s16_2ch_Lch440Rch880.wav ${D}${ROOT_HOME}/test_wav/normal_sin/
    install -m 755 ${S}/test_wav/normal_sin/022050_s16_2ch_Lch440Rch880.wav ${D}${ROOT_HOME}/test_wav/normal_sin/
    install -m 755 ${S}/test_wav/normal_sin/044100_s16_2ch_Lch440Rch880.wav ${D}${ROOT_HOME}/test_wav/normal_sin/

    # Install MSIOF tp
    install -d ${D}${ROOT_HOME}/msiof_tp
    install -m 755 ${S}/msiof_tp/* ${D}${ROOT_HOME}/msiof_tp/


    # Install PTP tp
    install -m 755 ${S}/testptp ${D}${ROOT_HOME}/
}

FILES_${PN} = "${ROOT_HOME}/*"

RDEPENDS_${PN} = "bash"

