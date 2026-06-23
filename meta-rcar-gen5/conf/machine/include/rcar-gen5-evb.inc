SOC_FAMILY =. "rcar-gen5-evb:"

DEFAULTTUNE ?= "aarch64"
require conf/machine/include/arm/arch-arm64.inc
require conf/machine/include/rcar-gen5-common.inc

# Device tree for Ironhide
KERNEL_DEVICETREE = " \
    renesas/r8a78000-ironhide.dtb \
    renesas/r8a78000-ironhide-uio.dtb \
    renesas/r8a78000-ironhide-uio-ca.dtb \
    renesas/r8a78000-ironhide-coresight.dtb \
"

# X5H on Evaluation board u-boot configure
UBOOT_CONFIG ??= "ironhide perceptor"
UBOOT_CONFIG[ironhide] = "r8a78000_ironhide_defconfig"
UBOOT_CONFIG[perceptor] = "r8j78070_perceptor_defconfig"

EXTRA_IMAGEDEPENDS += "arm-trusted-firmware optee-os"

# Use Poky default util-linux version to 2.41
PREFERRED_VERSION_util-linux = "2.41"
