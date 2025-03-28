SUMMARY = "OpenAMP library"
DESCRIPTION = "OpenAMP is a library that facilitates communication between different processing units."
HOMEPAGE = "https://github.com/OpenAMP/open-amp"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=dfc0adf4d04cc738ba65b7d3f587dca5"

require include/rcar-bsp-path-common.inc

SRC_URI = "git://github.com/renesas-rcar/open-amp.git;protocol=https;branch=v2024.10/rcar_v2025.03"
SRCREV = "65650922c95dd28cc202c5605de8360d4bc07550"

S = "${WORKDIR}/git"

DEPENDS = "libmetal"

inherit cmake

do_configure() {
    cmake ${S} -DCMAKE_TOOLCHAIN_FILE=${S}/cmake/platforms/rcar_ca_linux.cmake -DWITH_APPS=ON -DCMAKE_INCLUDE_PATH=${STAGING_INCDIR} -DCMAKE_LIBRARY_PATH=${STAGING_LIBDIR} -DLIBMETAL_INCLUDE_DIR=${STAGING_INCDIR}/libmetal -DLIBMETAL_LIB=${STAGING_LIBDIR}/libmetal.so
}

FILES:${PN} = " \
    ${RENESAS_DATADIR}/lib/*.so* \
    ${RENESAS_DATADIR}/bin/* \
"

FILES:${PN}-dev = " \
    ${RENESAS_DATADIR}/include/* \
"

FILES:${PN}-staticdev = " \
    ${RENESAS_DATADIR}/lib/*a \
"

INSANE_SKIP:${PN} = "dev-so libdir"

