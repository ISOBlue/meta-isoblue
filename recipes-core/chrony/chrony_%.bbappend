FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://chrony.conf"

do_install_append() {
  install -m 0644 ${WORKDIR}/chrony.conf ${D}/etc/chrony.conf
}
