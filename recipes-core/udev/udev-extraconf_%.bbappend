FILESEXTRAPATHS_prepend := "${THISDIR}/files/:"

SRC_URI_append = " \
    file://50-bring-cell-up.rules \
    file://50-usb-gps.rules \
    file://60-usb-no-suspend.rules \
    file://70-isoblue2-can.rules \
    file://80-wakeoncan.rules \
"

do_install_append() {
	# install the custom udev rules
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0666 ${WORKDIR}/50-bring-cell-up.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0666 ${WORKDIR}/50-usb-gps.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0666 ${WORKDIR}/60-usb-no-suspend.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0666 ${WORKDIR}/70-isoblue2-can.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0666 ${WORKDIR}/80-wakeoncan.rules ${D}${sysconfdir}/udev/rules.d/
}
