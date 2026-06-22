DESCRIPTION = "Initscript for PCIe IPMMU performance"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "x5h_vpf|x5h_evb|ironhide|perceptor"

SRC_URI = "file://set_nvme_rq_affinity.sh"

S = "${UNPACKDIR}"

INITSCRIPT_NAME = "set_nvme_rq_affinity.sh"

inherit update-rc.d

do_compile[noexec] = "1"

do_install () {
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${S}/set_nvme_rq_affinity.sh ${D}${sysconfdir}/init.d/
}

RDEPENDS:${PN} = "bash"

