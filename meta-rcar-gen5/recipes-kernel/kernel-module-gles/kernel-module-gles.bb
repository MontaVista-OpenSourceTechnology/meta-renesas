DESCRIPTION = "Kernel module of PowerVR GPU"
LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = " \
    file://GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://MIT-COPYING;md5=8c2810fa6bfdc5ae5c15a0c1ade34054 \
"
inherit module
DEPENDS += "linux-renesas"
PN = "kernel-module-gles"
PR = "r0"

COMPATIBLE_MACHINE = "x5h|ironhide"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require include/rcar-gfx-common.inc

SRC_URI = "${GFX_URL}/raw/${BRANCH}/gfxdrv/GSX_KM_X5H.tar.bz2;\
sha256sum=e41ccc937edcb2136abc4329c955d772dd61b7054e3b646f767e40cafdede9c2"

SRC_URI:append = " file://blacklist.conf"

S = "${WORKDIR}/rogue_km"

KBUILD_DIR = "${S}/build/linux/r8a78000_linux"
KBUILD_OUTDIR = "binary_r8a78000_linux_nullws_drm_release/target_aarch64/kbuild"

B = "${KBUILD_DIR}"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OEMAKE += "CROSS_COMPILE=${CROSS_COMPILE}"

# Build GFX kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

module_do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    cd ${KBUILD_DIR}
    oe_runmake
}

module_do_install() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}
    cd ${KBUILD_DIR}
    oe_runmake DISCIMAGE="${D}" install
    rm ${D}/etc/powervr_ddk_install_km.log
    # Install blacklist config file
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 644 ${WORKDIR}/blacklist.conf ${D}${sysconfdir}/modprobe.d/blacklist.conf
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        mv ${D}/lib/modules/${KERNEL_VERSION}/* ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/
        rm -rf ${D}/lib
    fi
}

# Ship the module symbol file to kernel build dir
SYSROOT_PREPROCESS_FUNCS = "module_sysroot_symbol"

module_sysroot_symbol() {
    install -m 644 ${S}/${KBUILD_OUTDIR}/Module.symvers ${STAGING_KERNEL_BUILDDIR}/GLES.symvers
}

# Clean up the module symbol file
CLEANFUNCS = "module_clean_symbol"

module_clean_symbol() {
    rm -f ${STAGING_KERNEL_BUILDDIR}/GLES.symvers
}

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/pvrsrvkm.ko \
    ${sysconfdir}/modules-load.d \
    ${sysconfdir}/modprobe.d/blacklist.conf \
"

RPROVIDES:${PN} += "kernel-module-pvrsrvkm"

INSANE_SKIP:append = " buildpaths"
