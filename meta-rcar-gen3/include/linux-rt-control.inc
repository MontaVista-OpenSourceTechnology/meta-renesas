USE_LINUX_RT = "${@'1' if 'linux-rt' in '${MACHINE_FEATURES}' else '0'}"
