RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.1.102/rcar-5.2.0.rc11_vpf.rc26", \
    "v6.1.102/rcar-6.0.0.rc11", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "9a788097f7e7fcab0f7992e2a1ddea7357f30c8c", \
    "51c157a23fb7af1c3b6dd5e127f3c7721c47dd24", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.24.0"

# Renesas SDK version
SDK_VERSION = "v4.34.0"
