RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc20", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc19", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "45f7f75346bb60ea9838eb0e0a33f178adb62369", \
    "7ce93860034c69d9536390eedd5dee8a8eea755a", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.14.0"
