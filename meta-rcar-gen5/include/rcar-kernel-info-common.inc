RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "v6.12.80/rcar-6.1.0_vpf", \
    "v6.12.80/rcar-6.1.0", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h_vpf", \
    "5f294044d0427e2600b3aef09f907fd6aad074f7", \
    "550377fca9327630b21d6cb8975c9f2247189d06", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.30.0"

# Renesas SDK version
SDK_VERSION = "v4.40.0"
