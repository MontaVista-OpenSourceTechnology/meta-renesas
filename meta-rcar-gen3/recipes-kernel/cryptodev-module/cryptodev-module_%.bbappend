FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rcar-gen3 = " file://0001-Fix-build-for-Linux-5.10.235.patch"
