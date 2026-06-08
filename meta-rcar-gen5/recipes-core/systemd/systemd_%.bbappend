FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen5 = " \
    file://add-non-owned-item-for-safe-transitioning.patch \
    file://0001-Revert-pam_systemd-issue-context-OSC-sequences-when-.patch \
"
