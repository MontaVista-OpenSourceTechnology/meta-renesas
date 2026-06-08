SUMMARY = "Backfire kernel module from rt-test"
DESCRIPTION = "Kernel module used by rt-tests sendme to signal userspace through /dev/backfire."
SECTION = "tests"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit module

DEPENDS = "linux-renesas"
COMPATIBLE_MACHINE = "(salvator-x|h3ulcb|m3ulcb|m3nulcb|ebisu|draak|geist)"

SRCREV = "217cd8518c5f7777d490892aa9c765a6b2782cb5"
SRC_URI = " \
    git://git.kernel.org/pub/scm/utils/rt-tests/rt-tests.git;branch=main;protocol=https \
    file://0001-rt-tests-upgrade-backfire-for-kernel-v6.1.166.patch \
"

S = "${WORKDIR}/git"
B = "${S}/src/backfire"

PV = "2.6"
PR = "r0"

KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake -C ${STAGING_KERNEL_DIR} O=${STAGING_KERNEL_BUILDDIR} M=${B} \
        CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${KERNEL_AR}" \
        OBJCOPY="${KERNEL_OBJCOPY}" STRIP="${KERNEL_STRIP}" modules
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 0644 ${B}/backfire.ko \
        ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/backfire.ko
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/backfire.ko"
FILES:${PN}-dbg = ""
ALLOW_EMPTY:${PN}-dbg = "1"

RPROVIDES:${PN} += "kernel-module-backfire-${KERNEL_VERSION}"
