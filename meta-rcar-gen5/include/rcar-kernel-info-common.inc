RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc27", \
    "v6.1.102/rcar-6.0.0.rc12", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "c728dcecf817075b5389264c059dfeae26d7c6e5", \
    "ff9ce02daad0f5a4e64984d725f488faf3cf3e71", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.25.0"

# Renesas SDK version
SDK_VERSION = "v4.35.0"
