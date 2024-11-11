SRC_URI = "git://github.com/intel/${BPN};branch=main;protocol=https"

# Add support nativesdk
BBCLASSEXTEND_append_rcar-gen5 = " nativesdk"
