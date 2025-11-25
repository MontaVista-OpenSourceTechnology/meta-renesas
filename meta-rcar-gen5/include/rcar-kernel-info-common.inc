RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc21", \
    "v6.1.102/rcar-6.0.0.rc6", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "82a71fc7f297c902604c8b35d6ebe4bf02f2f60b", \
    "09acca3f54cd7dee8ebe20f3b6e357f7b9af949a", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.20.0"

# Renesas SDK version
SDK_VERSION = "v4.30.0"
