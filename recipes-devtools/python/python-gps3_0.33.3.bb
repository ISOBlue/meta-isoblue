# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)
#
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b3f4cc03cb8549623b4bee89feebe73"

HOMEPAGE = "https://github.com/wadda/gps3"
SUMMARY = "Python 2.7-3.5 clients for gpsd"

SRC_URI = "https://github.com/wadda/gps3/archive/master.zip"
SRC_URI[md5sum] = "b95e33546ddc3e96e407d47a953d57b0"
SRC_URI[sha256sum] = "0da1c224a9fe5e62afe0bdc27073dfaf9c168920847062905b35104d0fbd65ad"

S = "${WORKDIR}/gps3-master"

inherit setuptools

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python-gps3 python-misc"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python-argparse python-core python-io python-json python-subprocess python-threading"

do_install_append() {
    # remove some useless files before packaging
    rm -r ${D}${datadir}/gps3
}
