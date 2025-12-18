FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

require ${@"glmark2.inc" if "rcar-gen5" in d.getVar("OVERRIDES") else ""}

