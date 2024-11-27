RDEPENDS:${PN} += " \
    nativesdk-cmake \
    nativesdk-bmaptool \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'nativesdk-wayland nativesdk-wayland-dev', '', d)} \
"

# Do not install QEMU packages, it reduces SDK size
RDEPENDS:${PN}:remove = " \
    nativesdk-qemu \
    nativesdk-qemu-helper \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'nativesdk-wayland-tools', '', d)} \
"
