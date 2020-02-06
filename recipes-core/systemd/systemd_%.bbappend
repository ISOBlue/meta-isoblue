FILESEXTRAPATHS_prepend := "${THISDIR}/systemd:"

SRC_URI += " \
    file://can_up@.service \
    file://broker.service \
    file://can-d-mr-imp.service \
    file://can-d-mr-tra.service \
    file://can-log-limp.service \
    file://can-log-ltra.service \
    file://can-watchdog.service \
    file://get-pgns.service \
    file://get-presence.service \
    file://gps-log@.service \
    file://gps-log-watchdog.service \
    file://kafka-log-monitor.service \
    file://heartbeat.service \
    file://ssh-forward.service \
    file://topic.service \
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
	install -m 0644 ${WORKDIR}/can-watchdog.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/topic.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/gps-log-watchdog.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/kafka-log-monitor.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/heartbeat.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/get-pgns.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/get-presence.service ${D}${systemd_system_unitdir}/
	install -m 0644 ${WORKDIR}/zookeeper.service ${D}${systemd_system_unitdir}/

	# install custom services
	# the can_up@.service is taken care of by udev
	install -m 0644 ${WORKDIR}/can_up@.service ${D}${systemd_system_unitdir}/

	install -m 0644 ${WORKDIR}/gps-log@.service ${D}${systemd_system_unitdir}/gps-log@gps.service

  # use custom ports from isoblue2.conf
	install -m 0644 ${WORKDIR}/ssh-forward.service ${D}${systemd_system_unitdir}/ssh-forward.service
  sed -i "s/SSHPORT/${SSHPORT}/" ${D}${systemd_system_unitdir}/ssh-forward.service

  # use custom ports from isoblue2.conf
	install -m 0644 ${WORKDIR}/tunnel.service ${D}${systemd_system_unitdir}/tunnel.service
  sed -i "s/BROKERPORT/${BROKERPORT}/" ${D}${systemd_system_unitdir}/tunnel.service
  sed -i "s/ZKPORT/${ZKPORT}/" ${D}${systemd_system_unitdir}/tunnel.service

  # make symlinks
  ln -sf ${systemd_system_unitdir}/zookeeper.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/zookeeper.service
  ln -sf ${systemd_system_unitdir}/get-pgns.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/get-pgns.service
  ln -sf ${systemd_system_unitdir}/get-presence.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/get-presence.service
  ln -sf ${systemd_system_unitdir}/gps-log@gps.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/gps-log@gps.service
  ln -sf ${systemd_system_unitdir}/gps-log-watchdog.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/gps-log-watchdog.service
  ln -sf ${systemd_system_unitdir}/kafka-log-monitor.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/kafka-log-monitor.service
  ln -sf ${systemd_system_unitdir}/ssh-forward.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/ssh-forward.service
  ln -sf ${systemd_system_unitdir}/gpsd.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/gpsd.service
}

FILES_${PN} += " \
	${systemd_system_unitdir}/can_up@.service \
	${systemd_system_unitdir}/broker.service \
	${systemd_system_unitdir}/can-d-mr-imp.service \
	${systemd_system_unitdir}/can-d-mr-tra.service \
	${systemd_system_unitdir}/can-log-limp.service \
	${systemd_system_unitdir}/can-log-ltra.service \
	${systemd_system_unitdir}/can-watchdog.service \
	${systemd_system_unitdir}/get-pgns.service \
	${systemd_system_unitdir}/get-presence.service \
	${systemd_system_unitdir}/gps-log@gps.service \
	${systemd_system_unitdir}/gps-log-watchdog.service \
	${systemd_system_unitdir}/kafka-log-monitor.service \
	${systemd_system_unitdir}/heartbeat.service \
	${systemd_system_unitdir}/ssh-forward.service \
	${systemd_system_unitdir}/topic.service \
	${systemd_system_unitdir}/zookeeper.service \
"

# Let's not enable the can-watchdog.service by default

SYSTEMD_SERVICE_${PN} = " \
	zookeeper.service \
	get-pgns.service \
	get-presence.service \
	gps-log@gps.service \
  kafka-log-monitor.service \
	ssh-forward.service \
"

SYSTEMD_AUTO_ENABLE = "enable"
