SUMMARY = "Recipe for libegl"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "x5h_vpf|ironhide"

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
