RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.12.80/rcar-6.1.0_vpf", \
    "v6.12.80/rcar-6.1.0", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "b88700836be3c18ee4f8b30d18e18150571c317d", \
    "b5ce8d1a749fdcd2a640877a7d6b92e0b7e379bd", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.26.0"

# Renesas SDK version
SDK_VERSION = "v4.36.0"
