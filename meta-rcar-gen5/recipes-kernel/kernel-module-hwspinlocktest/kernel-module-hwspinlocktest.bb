DESCRIPTION = "Hardware Spinlock Unit Test Module"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

DEPENDS = "linux-renesas"
PN = "kernel-module-hwspinlocktest"
PR = "r0"

HWSPINLOCKTEST_DRV_URL = "github.com/sumananna/omap-hwspinlock-test.git"
BRANCH = "master"
SRCREV = "20f15e79a3a0197e06328c252d23aec225de21ed"

SRC_URI = " \
    git://${HWSPINLOCKTEST_DRV_URL};branch=${BRANCH};protocol=https \
    file://001-Add-support-for-RCar-X5H.patch \
"

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_install () {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 644 ${S}/rcar_hwspinlock_test.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
}

