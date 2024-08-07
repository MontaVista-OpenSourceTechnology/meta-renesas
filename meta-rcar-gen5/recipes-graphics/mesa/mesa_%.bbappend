# These below libraries are provided by gles-user-module
PACKAGECONFIG:remove:rcar-gen5 = "${@bb.utils.contains('MACHINE_FEATURES', 'gsx', 'egl gles', '', d)}"

do_install:append:rcar-gen5() {
    # Have to remove khrplatform.h file due to conflict with gles-user-module
    # even though libegl from mesa is removed
    if [ "${MACHINE_FEATURES}" =~ "gsx" ]; then
        rm -rf ${D}${includedir}/KHR
    fi
}

