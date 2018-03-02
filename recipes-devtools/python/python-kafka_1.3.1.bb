LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22efebb1e053dcc946f4b9d17f3cbbea \
                    file://docs/license.rst;md5=e42e27d49563dd691bf940ea9bca2608"

HOMEPAGE = "https://github.com/dpkp/kafka-python"
SUMMARY = "Pure Python client for Apache Kafka"

SRC_URI = "https://github.com/dpkp/kafka-python/archive/${PV}.tar.gz;downloadfilename=${PV}.tar.gz"
SRC_URI[md5sum] = "62aa85f36db8a12fd0e299b7cb85d486"
SRC_URI[sha256sum] = "571f58eb5dcad57e565911fe5452d0351eaae5537163030512f9853e235020e4"

S = "${WORKDIR}/kafka-python-${PV}"

inherit setuptools

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python-compression python-contextlib python-core python-io python-lang python-logging python-math python-multiprocessing python-netclient python-numbers python-re python-shell python-stringold python-subprocess python-threading python-unittest python-six"
