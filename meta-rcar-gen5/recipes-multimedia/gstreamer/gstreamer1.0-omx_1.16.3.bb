SUMMARY = "OpenMAX IL plugins for GStreamer"
DESCRIPTION = "Wraps available OpenMAX IL components and makes them available as standard GStreamer elements."
HOMEPAGE = "http://gstreamer.freedesktop.org/"
SECTION = "multimedia"

LICENSE = "LGPL-2.1-only"
LICENSE_FLAGS = "commercial"
LICENSE_FLAGS_ACCEPTED = "commercial"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://omx/gstomx.h;beginline=1;endline=22;md5=4b2e62aace379166f9181a8571a14882 \
"
require include/rcar-bsp-path-common.inc

SRC_URI = " \
    gitsm://github.com/renesas-rcar/gst-omx.git;branch=RCAR-GEN5/1.16.3;protocol=https \
    file://gstomx.conf \
"

SRCREV = "e0752e688bd7445e0652288911430791b2988359"


DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad virtual/libomxil"

inherit meson pkgconfig upstream-version-is-even

GSTREAMER_1_0_OMX_TARGET ?= "rcar"
GSTREAMER_1_0_OMX_CORE_NAME ?= "${libdir}/libomxr_core.so"

EXTRA_OEMESON += "-Dtarget=${GSTREAMER_1_0_OMX_TARGET}"
#EXTRA_OEMESON:append:rcar-gen5 = " -Dheader_path=${STAGING_DIR_TARGET}/usr/local/include"

do_configure:prepend:rcar-gen5() {
    cd ${S}
    install -m 0644 ${UNPACKDIR}/gstomx.conf ${S}/config/rcar/
    sed -i 's,@RENESAS_DATADIR@,${RENESAS_DATADIR},g' ${S}/config/rcar/gstomx.conf
    cd ${B}
}

python __anonymous () {
    omx_target = d.getVar("GSTREAMER_1_0_OMX_TARGET")
    if omx_target in ['generic', 'bellagio']:
        # Bellagio headers are incomplete (they are missing the OMX_VERSION_MAJOR,#
        # OMX_VERSION_MINOR, OMX_VERSION_REVISION, and OMX_VERSION_STEP macros);
        # appending a directory path to gst-omx' internal OpenMAX IL headers fixes this
        d.appendVar("CFLAGS", " -I${S}/omx/openmax")
    elif omx_target == "rpi":
        # Dedicated Raspberry Pi OpenMAX IL support makes this package machine specific
        d.setVar("PACKAGE_ARCH", d.getVar("MACHINE_ARCH"))
}

set_omx_core_name() {
	sed -i -e "s;^core-name=.*;core-name=${GSTREAMER_1_0_OMX_CORE_NAME};" "${D}${sysconfdir}/xdg/gstomx.conf"
}
do_install[postfuncs] += " set_omx_core_name "

FILES:${PN} += "${libdir}/gstreamer-1.0/*.so"
FILES:${PN}-staticdev += "${libdir}/gstreamer-1.0/*.a"

VIRTUAL-RUNTIME_libomxil ?= "libomxil"
