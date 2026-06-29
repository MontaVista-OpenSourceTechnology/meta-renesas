RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "v6.12.80/rcar-6.1.0_vpf", \
    "v6.12.80/rcar-6.1.0", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "308331c3ef741d63bda760bb93ed0919ab4d4f43", \
    "0bcb5baa8c908ae6793047a5b4ef93273dd520e8", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.26.0"

# Renesas SDK version
SDK_VERSION = "v4.37.0"
