FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rcar-gen3 = " file://0001-msg-Remove-TLV-from-PTP-event-messages.patch"
