SUMMARY = "libmetal library"
DESCRIPTION = "libmetal is a library that provides a common interface for inter-processor communication."
HOMEPAGE = "https://github.com/OpenAMP/libmetal"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=f4d5df0f12dcea1b1a0124219c0dbab4"

SRC_URI = "git://github.com/OpenAMP/libmetal.git;protocol=https;branch=v2024.10"
SRCREV = "f8a0e51aee8e6993b6df243422f705bd7fea6079"

S = "${WORKDIR}/git"

DEPENDS = "sysfsutils"

inherit cmake

