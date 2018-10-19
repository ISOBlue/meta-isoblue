SUMMARY = "Software for ISOBlue2"
HOMEPAGE = "http://https://github.com/ISOBlue/isoblue2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9510a4f737974c229a56e1db9b0142e6"

SRC_URI = "https://github.com/ISOBlue/isoblue2/archive/isoblue-software_${PV}.tar.gz;downloadfilename=isoblue-software_${PV}.tar.gz"
SRC_URI[md5sum] = "f34429a0251d9cc55da2fa3c2e22d84a"
SRC_URI[sha256sum] = "4bd14a2d94d1c3d4023db52463aacdac82279fd627ef0cd6de04a8321444fc81"
DEPENDS = "librdkafka avro-c libpthread-stubs"

S = "${WORKDIR}/isoblue2-isoblue-software_${PV}/software/producer"

inherit autotools

do_configure () {
	oe_runconf
}

do_compile () {
	oe_runmake
}

do_install () {
	# install the compiled program
	install -d ${D}/opt/bin
	install -m 0755 ${WORKDIR}/build/can_watchdog/can_watchdog ${D}/opt/bin/
	install -m 0755 ${WORKDIR}/build/kafka_can_log/kafka_can_log ${D}/opt/bin/
	install -m 0755 ${WORKDIR}/build/debug_tools/can_msg_rate ${D}/opt/bin/
	install -m 0755 ${WORKDIR}/build/debug_tools/heartbeat ${D}/opt/bin/

    # install the python script
    install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/software/producer/kafka_gps_log/kafka_gps_log.py ${D}/opt/bin/

	# install the scripts
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/misc/get_pgns.sh ${D}/opt/bin/
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/misc/get_presence.sh ${D}/opt/bin/
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/misc/gps_log_watchdog.sh ${D}/opt/bin/

    #install the schema directory
    install -d ${D}/opt/schema
    install -m 0644 ${WORKDIR}/isoblue2-isoblue-software_${PV}/software/schema/*.avsc ${D}/opt/schema
}

FILES_${PN} += " \
	/opt/bin/can_watchdog \
	/opt/bin/kafka_can_log \
	/opt/bin/heartbeat \
	/opt/bin/net_strength \
	/opt/bin/can_msg_rate \
	/opt/bin/get_pgns.sh \
	/opt/bin/get_presence.sh \
	/opt/bin/gps_log_watchdog.sh \
	/opt/bin/kafka_gps_log.py \
	/opt/schema/*.avsc \
"
