SUMMARY = "Recipe for libegl"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "x5h_vpf|x5h_evb|ironhide|perceptor"

DEPENDS = "gles-user-module \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgbm wayland-kms', '', d)} \
"

PR = "r0"

RDEPENDS:${PN} = " \
    gles-user-module \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgbm wayland-kms', '', d)} \
"

PROVIDES = "virtual/egl"
RPROVIDES:${PN} += " \
    libegl \
    libegl1 \
"

ALLOW_EMPTY:${PN} = "1"
