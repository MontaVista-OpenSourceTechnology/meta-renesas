FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
    file://0001-rt-tests-build-and-install-sendme.patch \
    file://0002-rt-tests-sendme-check-backfire-device-I-O.patch \
"

RRECOMMENDS:${PN} += "kernel-module-backfire"

FILES:${PN} += " \
    ${bindir}/sendme \
    ${mandir}/man8/sendme.8* \
"
