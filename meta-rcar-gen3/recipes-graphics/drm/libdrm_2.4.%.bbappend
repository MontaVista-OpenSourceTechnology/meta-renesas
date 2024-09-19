FILESEXTRAPATHS:prepend:rcar-gen3 := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen3 = " \
    file://0001-Add-sync_fence_info-and-sync_pt_info.patch \
    file://Add-libkms.patch \
"

PACKAGES:prepend:rcar-gen3 = "${PN}-kms "

PACKAGECONFIG:append:rcar-gen3 = " libkms"
PACKAGECONFIG:rcar-gen3[libkms] = "-Dlibkms=enabled,-Dlibkms=disabled"

FILES:${PN}-kms:rcar-gen3 = "${libdir}/libkms*.so.*"
