SUMMARY = "Miscellaneous files for ISOBlue2"
HOMEPAGE = "http://https://github.com/OATS-Group/isoblue2/"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
RDEPENDS_${PN} += "bash"

SRC_URI = " \
    file://pgns \
    file://id \
"

do_install () {
    # install the directory
    install -d ${D}/opt

    # copy the machine-id to file
    echo ${MACHINEID} > ${WORKDIR}/id

    # install the files
    install -m 0644 ${WORKDIR}/id ${D}/opt/id
    install -m 0644 ${WORKDIR}/pgns ${D}/opt/pgns
}

FILES_${PN} += " \
    /opt/id \
    /opt/pgns \
"
