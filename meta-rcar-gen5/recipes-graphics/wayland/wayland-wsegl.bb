SUMMARY = "wayland-wsegl library"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/waylandws.h;beginline=1;endline=22;md5=ebf7ec97b867b0329acbb2c4190fd7a9"

SRC_URI = "git://github.com/renesas-rcar/wayland-wsegl.git;branch=rcar_gen5;protocol=https"

SRCREV = "a68cd8599eabc2467e56e88558964df2c0068e2e"

COMPATIBLE_MACHINE = "x5h|ironhide"

DEPENDS = "libgbm wayland-kms libdrm wayland wayland-native wayland-protocols"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

PACKAGES = " \
    ${PN} \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${libdir}/libpvrWAYLAND_WSEGL.so.* \
    ${libdir}/*.so \
"
FILES:${PN}-dbg += "${libdir}/libpvrWAYLAND_WSEGL/.debug/*"
INSANE_SKIP:${PN} += "dev-so"

