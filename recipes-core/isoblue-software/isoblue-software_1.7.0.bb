SUMMARY = "Software for ISOBlue2"
HOMEPAGE = "http://https://github.com/ISOBlue/isoblue2/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9510a4f737974c229a56e1db9b0142e6"

SRC_URI = "https://github.com/ISOBlue/isoblue2/archive/oada_upload.zip;downloadfilename=isoblue-software_${PV}.zip"
SRC_URI[md5sum] = "770aca58a32f1cfbe33df1bd4a5a4cf4"
SRC_URI[sha256sum] = "28e6b174105b572e26d9dd06ca702050ab7bd2deb07fba0a8c1b6bd478825f41"
DEPENDS = "libpthread-stubs sqlite3"

S = "${WORKDIR}/isoblue2-isoblue-software_${PV}/software/producer"

inherit autotools

do_configure () {
	oe_runconf
}

do_compile () {
	oe_runmake
}

do_install () {
	# install compiled programs
	install -d ${D}/opt/bin
	install -m 0755 ${WORKDIR}/build/debug_tools/heartbeat ${D}/opt/bin/

	# install python scripts
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/software/producer/oada_upload/oada_upload.py ${D}/opt/bin/

	# install the shell scripts
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/misc/get_pgns.sh ${D}/opt/bin/
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/misc/get_presence.sh ${D}/opt/bin/
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/misc/reconnect_cell.sh ${D}/opt/bin/

	# Install node scripts

	# install the sleep hook script
	install -d ${D}/lib/systemd/system-sleep
	install -m 0755 ${WORKDIR}/isoblue2-isoblue-software_${PV}/misc/sleep_hook.sh ${D}/lib/systemd/system-sleep/
	
	# Install default config file
	install -m 0755 ${workdir}/isoblue2-isoblue-software_${PV}/conf/isoblue.cfg ${D}/opt/
}

FILES_${PN} += " \
	/opt/bin/heartbeat \
	/opt/bin/oada_upload.py\
	/opt/bin/get_pgns.sh \
	/opt/bin/get_presence.sh \
	/lib/systemd/system-sleep/sleep_hook.sh \
	/opt/bin/reconnect_cell.sh \
	/opt/isoblue.cfg
"
