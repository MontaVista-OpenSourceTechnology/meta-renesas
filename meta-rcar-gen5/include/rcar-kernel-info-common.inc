RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc24", \
    "v6.1.102/rcar-6.0.0.rc9", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "ae7bab3241091db02111e2bdc61654589a77fa4c", \
    "36a15d8767e28a4425a79b91c03ffca8a51f5a49", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.23.0"

# Renesas SDK version
SDK_VERSION = "v4.33.0"
