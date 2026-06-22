require include/gles-control.inc

PV = "1.1"

SUMMARY = "Startup script and systemd unit file for the Weston Wayland compositor"
LICENSE = "MIT"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen5 = " \
    file://weston.ini \
    file://weston.sh \
"


LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://init \
           file://weston.env \
           file://weston.ini \
           file://weston@.service \
           file://71-weston-drm.rules \
           file://weston-start"

S = "${UNPACKDIR}"

do_install() {
    install -Dm755 ${S}/init ${D}/${sysconfdir}/init.d/weston
    install -D -p -m0644 ${S}/weston.ini ${D}${sysconfdir}/xdg/weston/weston.ini
    install -Dm644 ${S}/weston.env ${D}${sysconfdir}/default/weston

    # Install Weston systemd service and accompanying udev rule
    install -D -p -m0644 ${S}/weston@.service ${D}${systemd_system_unitdir}/weston@.service
    sed -i -e s:/etc:${sysconfdir}:g \
        -e s:/usr/bin:${bindir}:g \
        -e s:/var:${localstatedir}:g \
        ${D}${systemd_unitdir}/system/weston@.service
    install -D -p -m0644 ${S}/71-weston-drm.rules \
        ${D}${sysconfdir}/udev/rules.d/71-weston-drm.rules
    # Install weston-start script
    install -Dm755 ${S}/weston-start ${D}${bindir}/weston-start
    sed -i 's,@DATADIR@,${datadir},g' ${D}${bindir}/weston-start
    sed -i 's,@LOCALSTATEDIR@,${localstatedir},g' ${D}${bindir}/weston-start
    if [ "${USE_WESTON_BACKEND_HEADLESS}" = "1" ]; then
        sed -e "/^After=/s/$/ dbus.service multi-user.target/" \
            -e "s/\$OPTARGS/--backend=headless-backend.so --use-pixman --debug \$OPTARGS/" \
            -i ${D}/${systemd_system_unitdir}/weston@.service
    fi
    if [ "X${USE_GLES}" = "X1" ]; then
        sed -e "/^After=/s/$/ dbus.service multi-user.target/" \
            -e "s/\$OPTARGS/--idle-time=0 \$OPTARGS/" \
            -i ${D}/${systemd_system_unitdir}/weston@.service
    fi

    install -d ${D}/${sysconfdir}/xdg/weston
    # install weston.ini as sample settings of gl-renderer
    install -m 644 ${S}/weston.ini ${D}/${sysconfdir}/xdg/weston/

    # Checking for ivi-shell configuration
    # If ivi-shell is enable, we will add its configs to weston.ini
#    if [ "X${USE_WAYLAND_IVI_SHELL}" = "X1" ]; then
#        sed -i '/repaint-window=34/c\repaint-window=34\nshell=ivi-shell.so' \
#            ${D}/${sysconfdir}/xdg/weston/weston.ini
#        sed -e '$a\\' \
#            -e '$a\[ivi-shell]' \
#            -e '$a\ivi-module=ivi-controller.so' \
#            -e '$a\ivi-input-module=ivi-input-controller.so' \
#            -e '$a\transition-duration=300' \
#            -e '$a\cursor-theme=default' \
#            -i ${D}/${sysconfdir}/xdg/weston/weston.ini
#    fi

    # Set XDG_RUNTIME_DIR to /run/user/$UID (e.g. run/user/0)
    install -d ${D}/${sysconfdir}/profile.d
    install -m 0755 ${S}/weston.sh ${D}/${sysconfdir}/profile.d/weston.sh

    # Add DP-1 output configuration for x5h_evb|ironhide|perceptor machine
    case "${MACHINE}" in
        x5h_evb|ironhide|perceptor)
        sed -e '$a\\' \
            -e '$a\[output]' \
            -e '$a\name=DP-1' \
            -e '$a\mode=2560x1440' \
            -i ${D}/${sysconfdir}/xdg/weston/weston.ini

        ;;
    esac

    # Fix weston.service and weston@.service run simultaneously.
    mv ${D}/${sysconfdir}/init.d/weston ${D}/${sysconfdir}/init.d/weston@
}

do_install:append:libc-musl_qemux86() {
        echo "WESTON_DISABLE_ATOMIC=Y" >> ${D}${sysconfdir}/default/weston
}

do_install:append:libc-musl_qemux86-64() {
        echo "WESTON_DISABLE_ATOMIC=Y" >> ${D}${sysconfdir}/default/weston
}

inherit update-rc.d features_check systemd

# rdepends on weston which depends on virtual/egl
REQUIRED_DISTRO_FEATURES = "opengl"

RDEPENDS:${PN} = "weston kbd"

INITSCRIPT_NAME = "weston"
INITSCRIPT_PARAMS = "start 9 5 2 . stop 20 0 1 6 ."

FILES:${PN} += "${sysconfdir}/xdg/weston/weston.ini ${systemd_system_unitdir}/weston@.service ${sysconfdir}/default/weston ${sysconfdir}/profile.d/weston.sh"

INITSCRIPT_NAME = "weston@"

CONFFILES:${PN} += "${sysconfdir}/xdg/weston/weston.ini ${sysconfdir}/default/weston"

SYSTEMD_SERVICE:${PN} = "weston@%i.service"
SYSTEMD_AUTO_ENABLE = "disable"

