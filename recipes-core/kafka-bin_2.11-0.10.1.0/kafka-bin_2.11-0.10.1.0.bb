SUMMARY = "Apache Kafka Binaries for ISOBlue2"
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e2f7bdde53eb25ce23d8153f3f207ad9"
RDEPENDS_${PN} += "bash"

SRC_URI += " \
		http://archive.apache.org/dist/kafka/0.10.1.0/kafka_${PV}.tgz \
		file://isoblueProducer.properties \
		file://server.properties \
		file://zookeeper.properties \
"

SRC_URI[md5sum] = "45c7d032324e16c2e19a7d904a4d65c6"
SRC_URI[sha256sum] = "6d9532ae65c9c8126241e7b928b118aaa3a694dab08069471f0e61f4f0329390"

S = "${WORKDIR}/kafka_${PV}"

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# Specify compilation commands here
	:
}

do_install () {
	# install directories
	install -d ${D}/opt/
	install -d ${D}/opt/kafka
	install -d ${D}/opt/kafka/bin
	install -d ${D}/opt/kafka/config
	install -d ${D}/opt/kafka/libs
	install -d ${D}/opt/kafka/logs

    # remove extra flag to make kafka broker work
    sed -i 's/-server //' ${S}/bin/kafka-run-class.sh

	# install the binaries
	install -m 0755 ${S}/bin/*.sh ${D}/opt/kafka/bin/

	# install the config
	install -m 0644 ${S}/config/*.properties ${D}/opt/kafka/config/
	install -m 0644 ${WORKDIR}/*.properties ${D}/opt/kafka/config/

    # modify the port
    sed -i "s/BROKERPORT/${BROKERPORT}/" ${D}/opt/kafka/config/isoblueProducer.properties

	# install the libs
	install -m 0644 ${S}/libs/*.jar* ${D}/opt/kafka/libs/
}

FILES_${PN} += " \
	/opt/kafka/bin/* \
	/opt/kafka/config/* \
	/opt/kafka/libs/* \
	/opt/kafka/logs/* \
"
