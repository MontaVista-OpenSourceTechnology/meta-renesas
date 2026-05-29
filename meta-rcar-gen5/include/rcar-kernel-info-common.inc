RENESAS_BSP_URL = " \
    git://github.com/renesas-rcar/linux-bsp.git;protocol=https"

RENESAS_BSP_BRANCH = "${@oe.utils.conditional("MACHINE", "x5h", \
    "v6.12.80/rcar-6.1.0_vpf", \
    "v6.12.80/rcar-6.1.0", d)}"
RENESAS_BSP_SRCREV = "${@oe.utils.conditional("MACHINE", "x5h", \
    "40281fc49851dae33b7a66f886beba8bcc8b74c1", \
    "45b80618049ff2de38afe433dff096facdc84f88", d)}"

# Renesas Yocto BSP version
BSP_RENESAS_VERSION = "Renesas-Yocto-v7.26.0"

# Renesas SDK version
SDK_VERSION = "v4.36.0"
