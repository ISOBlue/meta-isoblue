FILESEXTRAPATHS_prepend := "${THISDIR}/systemd:"

SRC_URI += " \
    file://can_up@.service \
    file://broker.service \
    file://can-d-mr-imp.service \
    file://can-d-mr-tra.service \
    file://can-log-limp.service \
    file://can-log-ltra.service \
    file://can-log-rimp.service \
    file://can-log-rtra.service \
    file://can-watchdog.service \
    file://get-pgns.service \
    file://get-presence.service \
    file://gps-log@.service \
    file://gps-log-watchdog.service \
    file://heartbeat.service \
    file://mirror.service \
    file://net-strength.service \
    file://ssh-forward.service \
    file://topic.service \
    file://tunnel@.service \
    file://zookeeper.service \
"

do_install_append() {
	# install the service to the directory first
	install -d ${D}${systemd_system_unitdir}/

	# install regular services
	install -m 0644 ${WORKDIR}/broker.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-d-mr-imp.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-d-mr-tra.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-limp.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-ltra.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-rimp.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-log-rtra.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/can-watchdog.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/topic.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/gps-log-watchdog.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/heartbeat.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/mirror.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/net-strength.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/get-pgns.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/get-presence.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/zookeeper.service ${D}${systemd_system_unitdir}/

	# install custom services
	# the can_up@.service is taken care of by udev
	install -m 0644 ${WORKDIR}/can_up@.service ${D}${systemd_system_unitdir}/

	install -m 0644 ${WORKDIR}/gps-log@.service ${D}${systemd_system_unitdir}/gps-log@gps.service
	install -m 0644 ${WORKDIR}/gps-log@.service ${D}${systemd_system_unitdir}/gps-log@remote.service

    # use custom ports from isoblue2.conf
	install -m 0644 ${WORKDIR}/ssh-forward.service ${D}${systemd_system_unitdir}/ssh-forward.service
    sed -i "s/SSHPORT/${SSHPORT}/" ${D}${systemd_system_unitdir}/ssh-forward.service

    # use custom ports from isoblue2.conf
	install -m 0644 ${WORKDIR}/tunnel@.service ${D}${systemd_system_unitdir}/tunnel@52.54.160.103.service
    sed -i "s/BROKERPORT/${BROKERPORT}/" ${D}${systemd_system_unitdir}/tunnel@52.54.160.103.service
    sed -i "s/ZKPORT/${ZKPORT}/" ${D}${systemd_system_unitdir}/tunnel@52.54.160.103.service
}

FILES_${PN} += " \
	${systemd_system_unitdir}/can_up@.service \
	${systemd_system_unitdir}/broker.service \
	${systemd_system_unitdir}/can-d-mr-imp.service \
	${systemd_system_unitdir}/can-d-mr-tra.service \
	${systemd_system_unitdir}/can-log-limp.service \
	${systemd_system_unitdir}/can-log-ltra.service \
	${systemd_system_unitdir}/can-log-rimp.service \
	${systemd_system_unitdir}/can-log-rtra.service \
	${systemd_system_unitdir}/can-watchdog.service \
	${systemd_system_unitdir}/get-pgns.service \
	${systemd_system_unitdir}/get-presence.service \
	${systemd_system_unitdir}/gps-log@gps.service \
	${systemd_system_unitdir}/gps-log@remote.service \
	${systemd_system_unitdir}/gps-log-watchdog.service \
	${systemd_system_unitdir}/heartbeat.service \
	${systemd_system_unitdir}/mirror.service \
	${systemd_system_unitdir}/net-strength.service \
	${systemd_system_unitdir}/ssh-forward.service \
	${systemd_system_unitdir}/topic.service \
	${systemd_system_unitdir}/tunnel@52.54.160.103.service \
	${systemd_system_unitdir}/zookeeper.service \
"

# Let's not enable the can-watchdog.service by default

SYSTEMD_SERVICE_${PN} = " \
	zookeeper.service \
	get-pgns.service \
	get-presence.service \
	gps-log@remote.service \
	gps-log@gps.service \
	ssh-forward.service \
	topic.service \
	tunnel@52.54.160.103.service \
"

SYSTEMD_AUTO_ENABLE = "enable"
