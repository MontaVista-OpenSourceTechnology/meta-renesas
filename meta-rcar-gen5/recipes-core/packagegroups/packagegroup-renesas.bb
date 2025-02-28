DESCRIPTION = "Rcar Gen5 specific packages"

LICENSE = "BSD-3-Clause & GPL-2.0-or-later & LGPL-2.0-or-later"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
    packagegroup-renesas \
"

CPURTT_PKGS = " \
    kernel-module-cpurttdrv3 \
"

GFX_PKGS = " \
    kernel-module-gles \
    gles-user-module \
    libegl \
    libgles2 \
"
VSPM_PKGS = " \
    kernel-module-vspm \
    kernel-module-vspm-if \
    vspmif-user-module \
    vspmif-tp-user-module \
"

WAYLAND_PKGS = " \
    libgbm \
    libgbm-dev \
    wayland-kms \
    wayland-wsegl \
"

# ADAS common packages: CMEM, CV lib
RDEPENDS:packagegroup-renesas = " \
    kernel-module-uio-pdrv-genirq \
    kernel-module-cmemdrv \
    kernel-module-cmemdrv-dev \
    udev-rules-cvlib \
    linux-renesas-uapi \
    bsp-config \
    capture \
    ${CPURTT_PKGS} \
    kernel-module-qos \
    qosif-user-module \
    qosif-tp-user-module \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gsx', '${GFX_PKGS}' '${WAYLAND_PKGS}', '', d)} \
    nvme-initscripts \
    bsp-test-apps \
    ${VSPM_PKGS} \
    pcie-test \
    kernel-module-pci-interface \
    pci-interface-lib \
    pci-interface-tp \
    libmetal \
    open-amp \
"
# Temporarily remove
RDEPENDS:packagegroup-renesas:remove = "${CPURTT_PKGS}"

