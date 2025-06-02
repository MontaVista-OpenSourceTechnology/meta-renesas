DESCRIPTION = "Linux kernel for the R-Car X5x based boards"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc
require include/rcar-kernel-info-common.inc

COMPATIBLE_MACHINE = "x5h|ironhide"

SRCREV = "${RENESAS_BSP_SRCREV}"
SRC_URI = "${RENESAS_BSP_URL};nocheckout=1;branch=${RENESAS_BSP_BRANCH} \
"
SRC_URI:append:r8a78000 = " \
    file://0001-arm64-mm-skip-reset_pmuserenr_el0-call-to-avoid-Sync.patch \
    file://0002-arm64-defconfig-disable-ARM64_SVE-to-avoid-exception.patch \
    file://0003-arm64-defconfig-disable-ARM64_PTR_AUTH-to-avoid-exce.patch \
    file://0004-arm64-defconfig-disable-ARM64_AMU_EXTN-to-avoid-exce.patch \
    file://0005-arm64-dts-renesas-Add-R8A78000-X5H-DTs.patch \
    file://0006-ARM-renesas-Add-R8A78000-Ironhide-board-code.patch \
    file://0007-Revert-pinctrl-renesas-Initial-R8A78000-R-Car-X5H-PF.patch \
    file://0008-pinctrl-renesas-Allow-drive-strength-configuration-v.patch \
    file://0009-pinctrl-renesas-Show-bit-position-in-config-write.patch \
    file://0010-pinctrl-renesas-Initial-R8A78000-R-Car-X5H-PFC-suppo.patch \
    file://0011-pinctrl-renesas-r8a78000-Add-pins-groups-functions-f.patch \
    file://0012-Revert-gpio-gpio-rcar-Fix-and-remove-some-functions.patch \
    file://0013-Revert-gpio-gpio-rcar-Temporarily-update-offset-resg.patch \
    file://0014-gpio-gpio-rcar-Add-setting-enable-disable-input.patch \
    file://0015-gpio-gpio-rcar-Update-get-pin-state.patch \
    file://0016-gpio-gpio-rcar-Update-to-get-registers-through-struc.patch \
    file://0017-gpio-gpio-rcar-Support-for-gen5.patch \
    file://0018-arm64-dts-renesas-r8a78000-Add-I2C-EEPROM-device.patch \
    file://0019-i2c-rcar-Use-fixed-clock-value-for-R-Car-Gen5.patch \
    file://0020-i2c-rcar-Temporarily-disable-PM-runtime.patch \
    file://0021-soc-renesas-rcar-rst-Add-support-for-R-Car-X5H.patch \
    file://0022-arm64-dts-renesas-r8a78000-Add-Reset-node.patch \
    file://0023-arm64-dts-renesas-r8a78000-add-RCLK-Watchdog-node.patch \
    file://0024-arm64-dts-renesas-r8a78000-Enable-RWDT-node.patch \
    file://0025-watchdog-Temporarily-skip-runtime-PM-setup.patch \
    file://0026-watchdog-use-fixed-clock-value-for-R-Car-Gen5.patch \
    file://0027-arm64-dts-renesas-ironhide-set-I2C0-clock-to-100-kHz.patch \
    file://0028-arm64-dts-renesas-r8a78000-Add-device-tree-node-for-.patch \
    file://0029-arm64-dts-renesas-ironhide-Enable-Multi-Protocol-PHY.patch \
    file://0030-arm64-dts-renesas-r8a78000-Add-device-tree-node-for-.patch \
    file://0031-arm64-dts-renesas-ironhide-Enable-Ethernet-PCS.patch \
    file://0032-arm64-dts-renesas-r8a78000-Add-Ethernet-Switch3-node.patch \
    file://0033-arm64-dts-renesas-ironhide-Add-Ethernet-support.patch \
    file://0034-phy-renesas-Add-Multi-Protocol-PHY-driver-for-R-Car-.patch \
    file://0035-net-phy-dp83869-Fix-STRAP_OPMODE-bitmask-per-datashe.patch \
    file://0036-phy-renesas-Add-PCS-driver-for-R-Car-X5H-R8A78000.patch \
    file://0037-net-renesas-rswitch3-Add-support-for-R-Car-X5H-r8a78.patch \
    file://0038-arm64-defconfig-Enable-Ethernet-1G-support-for-R-Car.patch \
    file://0039-Revert-PCI-dwc-plat-Add-R-Car-Gen5-PCIe-4.0-Host-and.patch \
    file://0040-arm64-dts-renesas-r8a78000-Add-PCIe4-device-tree-nod.patch \
    file://0041-arm64-dts-renesas-ironhide-Add-PCIe4-device-tree-nod.patch \
    file://0042-PCI-dwc-rcar-Add-PCIe4-Host-driver-for-R-Car-Gen5.patch \
    file://0043-arm64-defconfig-Update-PCIe4-config.patch \
    file://0044-PCIe-dwc-rcar-Integrate-MP-PHY-into-PCIe4-driver.patch \
    file://0045-PCIe-dwc-rcar-Rename-clkreq-to-perst-to-reflect-actu.patch \
    file://0046-PCIe-dwc-rcar-Temporarily-remove-PERST-handling-from.patch \
"

LINUX_VERSION ?= "6.1.102"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

# For generating defconfig
KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG = "defconfig"

PACKAGES += "${PN}-uapi"

do_install:append:rcar-gen5() {
    # Install R-Car specific UAPI headers
    install -d ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/rcar-ipmmu-domains.h ${D}/usr/include/linux/
    install -m 0644 ${STAGING_KERNEL_DIR}/include/uapi/linux/renesas_uioctl.h ${D}/usr/include/linux/

    # Install dmatest module
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    mv ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/dma/dmatest.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

# Deploy vmlinux to deploy directory
do_deploy:append:rcar-gen5() {
    install -m 0644 ${KERNEL_OUTPUT_DIR}/vmlinux $deployDir/
}

FILES:${PN}-uapi = "/usr/include"

# uio_pdrv_genirq and dmatest configuration
KERNEL_MODULE_AUTOLOAD:append = " uio_pdrv_genirq dmatest"
KERNEL_MODULE_PROBECONF:append = " uio_pdrv_genirq dmatest i3c-rcar-master"
module_conf_uio_pdrv_genirq:append = ' options uio_pdrv_genirq of_id="generic-uio"'
module_conf_i3c-rcar-master = "blacklist i3c-rcar-master"

