FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:r8a78000 = " file://0001-stty-Add-support-for-non-standard-baudrates.patch"

# Make stty's priority higher than coreutils
ALTERNATIVE_PRIORITY[stty] = "110"
