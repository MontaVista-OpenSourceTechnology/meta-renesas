RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "v6.12.80/rcar-6.1.0_vpf", \
    "v6.12.80/rcar-6.1.0", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "308331c3ef741d63bda760bb93ed0919ab4d4f43", \
    "c770e53097e1ec584ceb6d9d73f8f5c2b446ee8c", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.27.0-MDP"

# Renesas SDK version
SDK_VERSION = "v4.37.0-MDP"
