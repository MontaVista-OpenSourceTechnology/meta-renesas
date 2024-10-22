DESCRIPTION = "Memory Manager Kernel module for Renesas R-Car Gen3"

require mmngr_drv.inc
require include/dtv-dvd-control.inc

DEPENDS = "linux-renesas"
PN = "kernel-module-mmngr"
PR = "r0"

S = "${WORKDIR}/git"
B = "${WORKDIR}/git/mmngr_drv/mmngr/mmngr-module/files/mmngr/drv"

MMNGR_CFG:salvator-x = "MMNGR_SALVATORX"
MMNGR_CFG:h3ulcb = "MMNGR_SALVATORX"
MMNGR_CFG:m3ulcb = "MMNGR_SALVATORX"
MMNGR_CFG:m3nulcb = "MMNGR_SALVATORX"
MMNGR_CFG:ebisu = "MMNGR_EBISU"

includedir = "${RENESAS_DATADIR}/include"
SSTATE_ALLOW_OVERLAP_FILES += "${STAGING_INCDIR}"

# Build Memory Manager kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile:prepend() {
    export MMNGR_CONFIG=${MMNGR_CFG}

    if [ "X${USE_DTV}" = "X1" ]; then
        export MMNGR_SSP_CONFIG="MMNGR_SSP_ENABLE"
    else
        export MMNGR_SSP_CONFIG="MMNGR_SSP_DISABLE"
    fi

    export MMNGR_IPMMU_MMU_CONFIG="IPMMU_MMU_DISABLE"
    export MMNGR_VALIDATE_CONFIG="MMNGR_ADDRESS_VALIDATION"

    install -d ${INCSHARED}
}

do_install () {
    # Create destination directories
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}
    if [ ! -d "${KERNELSRC}/mmngr" ]; then
        install -d ${KERNELSRC}/mmngr/
    fi

    # Install shared library to KERNELSRC(STAGING_KERNEL_DIR) for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${B}/Module.symvers ${KERNELSRC}/mmngr/mmngr.symvers

    # Install kernel module
    install -m 644 ${B}/mmngr.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install shared header files to KERNELSRC(STAGING_KERNEL_DIR)
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 644 ${B}/../include/mmngr_public.h ${KERNELSRC}/include/
    install -m 644 ${B}/../include/mmngr_private.h ${KERNELSRC}/include/
    install -m 644 ${B}/../include/mmngr_public_cmn.h ${KERNELSRC}/include/
    install -m 644 ${B}/../include/mmngr_private_cmn.h ${KERNELSRC}/include/
    install -m 644 ${B}/../include/mmngr_validate.h ${KERNELSRC}/include/

    # Install shared header file to ${includedir}
    install -m 644 ${B}/../include/mmngr_public_cmn.h ${D}/${includedir}/
    install -m 644 ${B}/../include/mmngr_private_cmn.h ${D}/${includedir}/
    install -m 644 ${B}/../include/mmngr_validate.h ${D}/${includedir}/
}

do_populate_sysroot[sstate-inputdirs] += "${B}/../include/"
do_populate_sysroot[sstate-outputdirs] += "${KERNELSRC}/include/"
do_populate_sysroot_setscene[prefuncs] = "mmngr_sstate_check_func"
SSTATE_ALLOW_OVERLAP_FILES = "${KERNELSRC}/include"

mmngr_sstate_check_func() {
    # An error is returned when unpack of kernel source has not been completed yet.
    # By returning error, rebuild task runs by force (Invalidating sstate).
    # This module installs shared header files in ${KERNELSRC}/include by
    # sstate cache.
    # Those files will be deleted by unpack task of kernel.
    if [ ${WITHIN_EXT_SDK} -eq 1 ]; then
        :
    else
        if [ ! -d "${KERNELSRC}/include" ]; then
            exit 1
        fi
    fi
}

# Should also clean ${KERNELSRC}/mmngr/ directory
do_clean[cleandirs] += "${KERNELSRC}/mmngr/"

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/mmngr.ko \
"

FILES:${PN}-dbg = "" 
ALLOW_EMPTY:${PN}-dbg = "1" 

RPROVIDES:${PN} += "kernel-module-mmngr"
