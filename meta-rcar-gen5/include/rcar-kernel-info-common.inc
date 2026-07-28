RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "v6.12.80/rcar-6.1.0_vpf", \
    "v6.12.80/rcar-6.1.0", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "02acdaed45ff7e18f80a7185b6912c406598e0c3", \
    "3338438a9bd3cf197cf085a523309ebf8d9a0036", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.28.0"

# Renesas SDK version
SDK_VERSION = "v4.38.0"
