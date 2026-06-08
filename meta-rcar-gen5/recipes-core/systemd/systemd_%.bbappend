FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen5 = " \
    file://add-non-owned-item-for-safe-transitioning.patch \
    file://0001-Revert-pam_systemd-issue-context-OSC-sequences-when-.patch \
    file://0002-exec-invoke-disable-OSC-3008-context.patch \
"
