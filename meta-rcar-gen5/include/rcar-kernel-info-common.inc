RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc23", \
    "v6.1.102/rcar-6.0.0.rc8", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "b45c03895e162dfc70356a29eba606f76ddb71f2", \
    "3c9b6901ad7084029d2f89ec7ecf5f1d43dbbb99", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.21.0"

# Renesas SDK version
SDK_VERSION = "v4.31.0"
