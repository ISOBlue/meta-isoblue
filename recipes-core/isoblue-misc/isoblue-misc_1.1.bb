SUMMARY = "Miscellaneous files for ISOBlue2"
HOMEPAGE = "http://https://github.com/OATS-Group/isoblue2/"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} += "bash"

SRC_URI = " \
    file://pgns \
    file://id \
    file://chrony.conf \
    file://dnsmasq.conf \
    file://interfaces \
    file://resolv.conf.dnsmasq \
"

do_install () {
    # install the directory
    install -d ${D}/opt
    install -d ${D}/etc
    install -d ${D}/etc/network

    # copy the machine-id to file
    echo ${MACHINEID} > ${WORKDIR}/id

    # install the files
    install -m 0644 ${WORKDIR}/id ${D}/opt/id
    install -m 0644 ${WORKDIR}/pgns ${D}/opt/pgns
    install -m 0644 ${WORKDIR}/resolv.conf.dnsmasq ${D}/opt/resolv.conf.dnsmasq
    install -m 0644 ${WORKDIR}/chrony.conf ${D}/etc/chrony.conf
    install -m 0644 ${WORKDIR}/dnsmasq.conf ${D}/etc/dnsmasq.conf
    install -m 0644 ${WORKDIR}/interfaces ${D}/etc/network/interfaces
}

FILES_${PN} += " \
    /opt/id \
    /opt/pgns \
    /opt/resolv.chrony.conf \
    /etc/chrony.conf \
    /etc/dnsmasq.conf \
    /etc/network/interfaces \
"
