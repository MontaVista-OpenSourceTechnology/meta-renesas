RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "v6.12.80/rcar-6.1.0_vpf", \
    "v6.12.80/rcar-6.1.0", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "02acdaed45ff7e18f80a7185b6912c406598e0c3", \
    "855a8c35194587be2eea11663c45a59746f999ae", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.28.0"

# Renesas SDK version
SDK_VERSION = "v4.38.0"
