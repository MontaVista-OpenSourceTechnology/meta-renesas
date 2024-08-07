FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen5 = " file://add-non-owned-item-for-safe-transitioning.patch"
