RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc22", \
    "v6.1.102/rcar-6.0.0.rc7", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "d014d011ad0f9baf5ce8fa60e6ad0cc0ce64a933", \
    "c892f4ab2704f97b2c53f8fa23dacecdfb5760ee", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.20.0"

# Renesas SDK version
SDK_VERSION = "v4.30.0"
