SUMMARY = "Nginx helper for octoprint and mjpg-streamer"

LICENSE = "AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=73f1eb20517c55bf9493b7dd6e480788"

PV = "1.0"

SRC_URI = "file://nginx.conf \
           file://LICENSE \
          "

do_unpack2() {
    cp ${WORKDIR}/LICENSE ${S}
}

addtask unpack2 before do_populate_lic after do_unpack

do_install() {
    install -d ${D}${sysconfdir}/nginx/
    install -m 0644 ${WORKDIR}/nginx.conf ${D}${sysconfdir}/nginx/nginx.conf.octoprint	
}

pkg_postinst:${PN} () {
    mv $D${sysconfdir}/nginx/nginx.conf $D${sysconfdir}/nginx/nginx.conf.pre-octoprint
    cp $D${sysconfdir}/nginx/nginx.conf.octoprint $D${sysconfdir}/nginx/nginx.conf
}

RDEPENDS:${PN} = "octoprint mjpg-streamer nginx"
