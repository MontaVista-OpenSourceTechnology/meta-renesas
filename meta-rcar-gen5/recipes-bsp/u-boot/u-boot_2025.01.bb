HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
PE = "1"

S = "${WORKDIR}/git"

require u-boot.inc

DEPENDS += "flex-native bison-native bc-native dtc-native lzop-native srecord-native gnutls-native"

UBOOT_URL = "git://github.com/renesas-rcar/u-boot.git;protocol=https"
BRANCH = "v2025.01/rcar-7.0.0.rc2"

SRC_URI = "${UBOOT_URL};branch=${BRANCH}"
SRCREV = "ad4e16cb4a9cc88b469bef8ff3e80f9657031786"

PV = "v2025.01+git${SRCPV}"

SRC_URI:append:r8a78000 = " \
    file://0001-arm64-renesas-Revert-the-initial-Renesas-R-Car-R8A78.patch \
    file://0002-pinctrl-renesas-Allow-drive-strength-configuration-v.patch \
    file://0003-pinctrl-renesas-Show-bit-position-in-config-write.patch \
    file://0004-pinctrl-renesas-Initial-R8A78000-R-Car-X5H-PFC-suppo.patch \
    file://0005-pinctrl-renesas-r8a78000-Add-HSCIF-pins-groups-funct.patch \
    file://0006-pinctrl-renesas-r8a78000-Add-SCIF-pins-groups-functi.patch \
    file://0007-pinctrl-renesas-r8a78000-Add-I2C-pins-groups-functio.patch \
    file://0008-pinctrl-renesas-r8a78000-Add-INTC-EX-pins-groups-fun.patch \
    file://0009-pinctrl-renesas-r8a78000-Add-PCIE-pins-groups-functi.patch \
    file://0010-pinctrl-renesas-r8a78000-Add-QSPI-pins-groups-functi.patch \
    file://0011-pinctrl-renesas-r8a78000-Add-SD-MMC-pins-groups-func.patch \
    file://0012-pinctrl-renesas-r8a78000-Add-Ethernet-pins-groups-fu.patch \
    file://0013-arm64-renesas-Add-R8A78000-X5H-Kconfig-entry-and-PRR.patch \
    file://0014-arm64-renesas-Use-reset-macro-from-common-header.patch \
    file://0015-arm64-renesas-Extend-stub-PSCI-implementation-to-R-C.patch \
    file://0016-gpio-gpio-rcar-Add-R-Car-Gen5-support.patch \
    file://0017-i2c-rcar_i2c-Add-R-Car-Gen5-support.patch \
    file://0018-dt-bindings-power-Add-R8A78000-power-domain-definiti.patch \
    file://0019-dt-bindings-clock-Add-R8A78000-clock-definitions.patch \
    file://0020-dt-bindings-clock-Add-R8A78000-reset-definitions.patch \
    file://0021-arm64-dts-renesas-Add-R8A78000-X5H-DTs.patch \
    file://0022-arm64-renesas-Make-CONFIG_SYS_LOAD_ADDR-family-speci.patch \
    file://0023-arm64-renesas-Add-R8A78000-Ironhide-board-code.patch \
    file://0024-arm64-renesas-gen5-common-Allow-WDT-reset.patch \
    file://0025-arm64-renesas-dts-r8a78000-add-dummy-scif-clock.patch \
    file://0026-arm64-dts-ironhide-Add-serial-console-pin-control.patch \
    file://0027-arm64-dts-renesas-r8a78000-Add-I2C-nodes.patch \
    file://0028-arm64-dts-renesas-ironhide-Add-I2C0-I2C1-and-EEPROM.patch \
    file://0029-arm64-dts-renesas-r8a78000-Add-GPIO-nodes.patch \
    file://0030-gpio-gpio-rcar-Skip-clock-setting.patch \
    file://0031-i2c-rcar_i2c-Use-fixed-clock-value-for-R-Car-Gen5.patch \
    file://0032-net-rswitch3-Add-initial-driver-for-Renesas-Ethernet.patch \
    file://0033-phy-renesas-Add-PCS-driver-for-R-Car-X5H-R8A78000.patch \
    file://0034-phy-renesas-Add-Multi-Protocol-PHY-driver-for-R-Car-.patch \
    file://0035-net-phy-TI-DP83869-Update-STRAP_OPMODE-bitmask.patch \
    file://0036-configs-ironhide-Enable-Ethernet-1G-support-for-R-Ca.patch \
    file://0037-arm64-dts-renesas-r8a78000-Add-Multi-Protocol-PHY-no.patch \
    file://0038-arm64-dts-renesas-ironhide-Enable-Multi-Protocol-PHY.patch \
    file://0039-arm64-dts-renesas-r8a78000-Add-Ethernet-PCS-node.patch \
    file://0040-arm64-dts-renesas-ironhide-Enable-Ethernet-PCS.patch \
    file://0041-arm64-dts-renesas-r8a78000-Add-Ethernet-Switch3-node.patch \
    file://0042-arm64-dts-renesas-ironhide-Add-Ethernet-support.patch \
    file://0043-arm64-renesas-gen5-common-Add-baremetal-clock-and-mo.patch \
    file://0044-configs-ironhide-Enable-Baremetal-clk-and-module-con.patch \
    file://0045-mmc-renesas-sdhi-Add-compatible-string-for-R-Car-X5H.patch \
    file://0046-mmc-renesas-sdhi-Skip-clock-setting.patch \
    file://0047-configs-ironhide-Downgrade-SD-MMC-from-UHS-HS200.patch \
    file://0048-arm64-dts-renesas-r8a78000-Add-SD-MMC-node.patch \
    file://0049-arm64-dts-renesas-ironhide-Add-eMMC-support.patch \
    file://0050-configs-ironhide-Store-environment-in-MMC.patch \
    file://0051-arm64-renesas-gen5-common-Add-clock-pcie4.patch \
    file://0052-configs-ironhide-Increase-SYS_CBSIZE-to-10KiB.patch \
"

UBOOT_SREC_SUFFIX = "srec"
UBOOT_SREC ?= "u-boot-elf.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_IMAGE ?= "u-boot-elf-${MACHINE}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_SYMLINK ?= "u-boot-elf-${MACHINE}.${UBOOT_SREC_SUFFIX}"

do_deploy:append() {
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    type=${type#*_}
                    install -m 644 ${B}/${config}/${UBOOT_SREC} ${DEPLOYDIR}/u-boot-elf-${type}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}
                    cd ${DEPLOYDIR}
                    ln -sf u-boot-elf-${type}-${PV}-${PR}.${UBOOT_SREC_SUFFIX} u-boot-elf-${type}.${UBOOT_SREC_SUFFIX}
                fi
            done
            unset j
        done
        unset i
    else
        install -m 644 ${B}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}
        cd ${DEPLOYDIR}
        rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
        ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
        ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
    fi
}
