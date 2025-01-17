DESCRIPTION = "ChirpStack Network Server"
HOMEPAGE = "https://www.chirpstack.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3a340e43ab9867d3e5d0ea79a54b0e1"
SRC_URI = " \
    https://artifacts.chirpstack.io/downloads/chirpstack-network-server/chirpstack-network-server_${PV}_linux_armv5.tar.gz \
    file://chirpstack-network-server.init \
    file://chirpstack-network-server.monit \
    file://config/au915_0.toml \
    file://config/au915_1.toml \
    file://config/au915_2.toml \
    file://config/au915_3.toml \
    file://config/au915_4.toml \
    file://config/au915_5.toml \
    file://config/au915_6.toml \
    file://config/au915_7.toml \
    file://config/eu868.toml \
    file://config/us915_0.toml \
    file://config/us915_1.toml \
    file://config/us915_2.toml \
    file://config/us915_3.toml \
    file://config/us915_4.toml \
    file://config/us915_5.toml \
    file://config/us915_6.toml \
    file://config/us915_7.toml \
"
SRC_URI[md5sum] = "eb82e6711c8eedb36a6229c8f4e655f9"
SRC_URI[sha256sum] = "e109447c9faa7ed1124a8d49d89768d22801f30df6d3feca399ed4c70b20665f"
PR = "r1"

inherit update-rc.d

INITSCRIPT_NAME = "chirpstack-network-server"
INITSCRIPT_PARAMS = "defaults"

S = "${WORKDIR}"
BIN_DIR = "/opt/chirpstack-network-server"
CONF_DIR = "${sysconfdir}/chirpstack-network-server"

do_install() {
    install -d ${D}${BIN_DIR}
    install -m 0755 chirpstack-network-server ${D}${BIN_DIR}/

    install -d ${D}${CONF_DIR}
    install -d ${D}${CONF_DIR}/config

    install -m 0640 ${WORKDIR}/config/* ${D}${CONF_DIR}/config

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/chirpstack-network-server.init ${D}${sysconfdir}/init.d/chirpstack-network-server

    install -d ${D}${sysconfdir}/monit.d
    install -m 0644 ${WORKDIR}/chirpstack-network-server.monit ${D}${sysconfdir}/monit.d/chirpstack-network-server
}

FILES_${PN} += "${BIN_DIR}"
FILES_${PN}-dbg += "${BIN_DIR}/.debug"

