SUMMARY = "Camera application test"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=9504a7b7666faec5abd046d28a69450e"

DEPENDS:rcar-gen5 = "libdrm kernel-module-cmemdrv opencv"

inherit pkgconfig

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/renesas-rcar/capture.git;protocol=https;nobranch=1"

SRCREV:x5h = "1271dfe4c4d1acb2a13b3bd1461aaf2ff8c2bf6f"
SRCREV:r8a78000 = "4000d08e702a61ea7a37854f775d3cf021b43b8c"

do_compile() {
    cd ${S}
    make all || die
}

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/capture ${D}${bindir}

    install -d ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_0.sh   ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_4.sh   ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_0-3.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_camera_4-7.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_2cameras_on_display1920x1080.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_4cameras_on_display1920x1080.sh ${D}/usr/share/tests/
    install -m 755 ${S}/test_lvds_8cameras_on_display1920x1080.sh ${D}/usr/share/tests/
}

FILES:${PN} = " \
    ${bindir}/capture \
    /usr/share/tests/test_lvds_camera_0.sh \
    /usr/share/tests/test_lvds_camera_4.sh \
    /usr/share/tests/test_lvds_camera_0-3.sh \
    /usr/share/tests/test_lvds_camera_4-7.sh \
    /usr/share/tests/test_lvds_2cameras_on_display1920x1080.sh \
    /usr/share/tests/test_lvds_4cameras_on_display1920x1080.sh \
    /usr/share/tests/test_lvds_8cameras_on_display1920x1080.sh \
"

INSANE_SKIP:${PN} += "ldflags"

