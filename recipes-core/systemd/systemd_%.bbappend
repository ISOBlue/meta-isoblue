FILESEXTRAPATHS_prepend := "${THISDIR}/systemd:"

SRC_URI += " \
    file://can_up@.service \
    file://can-d-mr-imp.service \
    file://can-d-mr-tra.service \
    file://can-log-limp.service \
    file://can-log-ltra.service \
    file://can-log-rimp.service \
    file://can-log-rtra.service \
    file://can-watchdog.service \
    file://get-pgns.service \
    file://get-presence.service \
    file://heartbeat.service \
"

do_install_append() {
	# install the service to the directory first
	install -d ${D}${systemd_system_unitdir}/

	# install regular services
	install -m 0644 ${WORKDIR}/can-d-mr-imp.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-d-mr-tra.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-limp.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-ltra.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-rimp.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-rtra.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-watchdog.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/heartbeat.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/get-pgns.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/get-presence.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/gps_log.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/oada_upload.service ${D}${systemd_system_unitdir}/


	# install custom services
	# the can_up@.service is taken care of by udev
	install -m 0644 ${WORKDIR}/can_up@.service ${D}${systemd_system_unitdir}/

	# make symlinks
	ln -sf ${systemd_system_unitdir}/get-pgns.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/get-pgns.service
	ln -sf ${systemd_system_unitdir}/get-presence.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/get-presence.service
	ln -sf ${systemd_system_unitdir}/gpsd.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/gpsd.service
	ln -sf ${systemd_system_unitdir}/gps_log.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/gps_log.service
	ln -sf ${systemd_system_unitdir}/oada_upload.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/oada_upload.service


}

FILES_${PN} += " \
	${systemd_system_unitdir}/can_up@.service \
	${systemd_system_unitdir}/can-d-mr-imp.service \
	${systemd_system_unitdir}/can-d-mr-tra.service \
	${systemd_system_unitdir}/can-log-limp.service \
	${systemd_system_unitdir}/can-log-ltra.service \
	${systemd_system_unitdir}/can-log-rimp.service \
	${systemd_system_unitdir}/can-log-rtra.service \
	${systemd_system_unitdir}/can-watchdog.service \
	${systemd_system_unitdir}/get-pgns.service \
	${systemd_system_unitdir}/get-presence.service \
	${systemd_system_unitdir}/heartbeat.service \
	${systemd_system_unitdir}/gps_log.service \
	${systemd_system_unitdir}/oada_upload.service \
"

SYSTEMD_AUTO_ENABLE = "enable"
