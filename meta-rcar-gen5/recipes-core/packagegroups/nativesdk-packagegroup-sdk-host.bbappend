RDEPENDS:${PN} += " \
    nativesdk-cmake \
    nativesdk-bmap-tools \
"

# Do not install QEMU packages, it reduces SDK size
RDEPENDS:${PN}:remove = " \
    nativesdk-qemu \
    nativesdk-qemu-helper \
"
