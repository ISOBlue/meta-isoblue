# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)
#
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-License-2.0"
LIC_FILES_CHKSUM = "file://src/avro/LICENSE;md5=b1e01b26bacfc2232046c90a330332b3"

HOMEPAGE = "http://avro.apache.org/"
SUMMARY = "Avro is a serialization and RPC framework."

SRC_URI = "http://archive.apache.org/dist/avro/avro-${PV}/py/avro-${PV}.tar.gz"
SRC_URI[md5sum] = "44456d2c47d44d854e2ac5a9d9d9253c"
SRC_URI[sha256sum] = "46f34322657281ae717b95c0d712bffa0736f1fa82642ddd7ccf9c3bec635508"

S = "${WORKDIR}/avro-${PV}"

inherit setuptools

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "snappy"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python-core python-crypt python-io python-json python-lang python-math python-netclient python-netserver python-textutils python-twisted"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    simplejson
#    snappy
#    twisted.internet
#    twisted.internet.defer
#    twisted.internet.protocol
#    twisted.web
#    twisted.web.client
#    twisted.web.http_headers
#    twisted.web.iweb
#    zope.interface
