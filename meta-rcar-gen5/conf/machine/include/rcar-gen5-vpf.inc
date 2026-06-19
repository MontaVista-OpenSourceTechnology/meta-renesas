SOC_FAMILY =. "rcar-gen5-vpf:"

DEFAULTTUNE ?= "aarch64"
require conf/machine/include/arm/arch-arm64.inc
require conf/machine/include/rcar-gen5-common.inc

# Device tree for X5H on Virtual Platform
KERNEL_DEVICETREE = " \
    renesas/r8a78000-rfs2x.dtb \
    renesas/r8a78000-rfs2x-uio.dtb \
    renesas/r8a78000-rfs2x-gsx.dtb \
    renesas/r8a78000-rfs2x-uio-gsx.dtb \
"

# X5H on Virtual Platform u-boot configure
UBOOT_MACHINE = "r8a78000_ironhide_defconfig"

BBMASK += "meta-renesas/meta-rcar-gen5/recipes-core/busybox/"

# Use Poky default util-linux version instead of custom 2.41
PREFERRED_VERSION_util-linux = "2.39.3"
