FILESEXTRAPATHS:prepend:rcar-gen3 := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen3 = " file://add-non-owned-item-for-safe-transitioning.patch"
