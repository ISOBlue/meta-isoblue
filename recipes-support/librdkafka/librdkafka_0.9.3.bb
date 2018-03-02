LICENSE = "MIT"
SECTION = "libs"
LIC_FILES_CHKSUM = "file://LICENSE.regexp;md5=8abca43e8b4acec9664239c3fb93a850 \
                    file://LICENSE.tinycthread;md5=73b02364ef0efde43fa600fcbaed39c2 \
                    file://LICENSE.xxhash;md5=48f2fdbb34d61f9982f7e5e161a3ce9b \
                    file://LICENSE.wingetopt;md5=5489a7a5b6cd6274c7201506577f7d54 \
                    file://LICENSE.pycrc;md5=b22d7353eb79f4e6659b7806942806da \
                    file://LICENCES.rtf;md5=428bd7a4c6acc48677e3a6331572357d \
                    file://LICENSE;md5=6baccb9e4e9e0044093be8d78d0a1087 \
                    file://LICENSE.queue;md5=8a8a7eea50138d5ddf6c95e1cb09ff57 \
                    file://LICENSE.snappy;md5=14e32b50fbf250e79966848a60c74302 \
                    file://packaging/debian/copyright;md5=1660a01c03043fa66e49e388177b479c"

SRC_URI = " \
    https://github.com/edenhill/librdkafka/archive/v${PV}.tar.gz;downloadfilename=v${PV}.tar.gz \
    file://0001-Remove-cpp-compilation-from-Makefile.patch;patch=1 \
"
SRC_URI[md5sum] = "45bc9713bd4ed948e1efbd62688fc502"
SRC_URI[sha256sum] = "745ead036f0d5b732e1cd035a1f31fc23665f2982bf9d799742034e0a1bd0be9"

DEPENDS = "jansson zlib libpthread-stubs openssl"

EXTRA_OEMAKE = "-lpthread"

TMPINC ?= "${STAGING_INCDIR}"
TMPLIB ?= "${STAGING_LIBEXECDIR}"

do_configure () {
	# Specify any needed configure commands here
	./configure --includedir=${TMPINC} --libdir=${TMPLIB}
}

do_compile () {
	# You will almost certainly need to add additional arguments here
	make libs
}

do_install () {
	install -d ${D}${libdir}/
	install -m 0644 ${S}/src/*.so* ${D}${libdir}/
	install -m 0644 ${S}/src/*.a ${D}${libdir}/

	install -d ${D}${includedir}/librdkafka/
	install -m 0644 ${S}/src/*.h ${D}${includedir}/librdkafka/
}

FILES_${PN} = "${libdir}/*.so \
               ${libdir}/*.a \
               ${includedir}/librdkafka/*.h"
FILES_${PN}_dev = "${includedir}"
PACKAGES = "${PN}"
