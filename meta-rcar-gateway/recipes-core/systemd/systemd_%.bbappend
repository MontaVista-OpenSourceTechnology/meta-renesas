FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://add-non-owned-item-for-safe-transitioning.patch"

do_install:append:rcar-gateway() {
    rm -f ${D}${libdir}/systemd/profile.d/80-systemd-osc-context.sh
    rm -f ${D}${sysconfdir}/profile.d/80-systemd-osc-context.sh
    ln -s /dev/null ${D}${sysconfdir}/tmpfiles.d/20-systemd-osc-context.conf
}
