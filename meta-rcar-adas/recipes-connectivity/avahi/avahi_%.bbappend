FILESEXTRAPATHS:prepend:rcar := "${THISDIR}/files:"

SRC_URI:append:rcar = " \
    file://ssh.service \
"

do_install:append:rcar() {
    install -m 644 ${UNPACKDIR}/ssh.service ${D}${sysconfdir}/avahi/services
}

FILES:avahi-daemon:append:rcar = " \
    ${sysconfdir}/avahi/services/ssh.service \
"
