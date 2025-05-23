FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rcar-gen5 = " \
    file://v4l-utils_Add_RAW2x_raw_bayer_pixel_formats.patch \
"
