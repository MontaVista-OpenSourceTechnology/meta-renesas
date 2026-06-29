SUMMARY = "OpenAMP library"
DESCRIPTION = "OpenAMP is a library that facilitates communication between different processing units."
HOMEPAGE = "https://github.com/OpenAMP/open-amp"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=dfc0adf4d04cc738ba65b7d3f587dca5"

SRC_URI = "git://github.com/renesas-rcar/open-amp.git;protocol=https;branch=v2024.10/rcar_v2026.06"
SRCREV = "cbc8320e01b294a4d8adb9e41cf4eab73bfeeece"


DEPENDS = "libmetal"

inherit cmake

EXTRA_OECMAKE = " \
    -DCMAKE_TOOLCHAIN_FILE=${S}/cmake/platforms/rcar_ca_linux.cmake \
    -DWITH_APPS=ON \
    -DCMAKE_INCLUDE_PATH=${STAGING_INCDIR} \
    -DCMAKE_LIBRARY_PATH=${STAGING_LIBDIR} \
    -DLIBMETAL_INCLUDE_DIR=${STAGING_INCDIR} \
    -DLIBMETAL_LIB=${STAGING_LIBDIR}/libmetal.so \
    -DCMAKE_C_FLAGS='-I${S}/lib/include' \
    -DCMAKE_POLICY_VERSION_MINIMUM=3.5 \
"

EXTRA_OECMAKE:append:rcar-gen5-vpf = " -DRFS2X_ENV=1"

FILES:${PN} = " \
    ${libdir}/*.so* \
    ${bindir}/* \
"

FILES:${PN}-dev = " \
    ${includedir}/* \
"

FILES:${PN}-staticdev = " \
    ${libdir}/*a \
"

INSANE_SKIP:${PN} = "dev-so buildpaths"
INSANE_SKIP:${PN}-staticdev = "buildpaths"

