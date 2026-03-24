$1 ~ /^[a-z]{3}[0-9]{3}$/ {
    commands[$1] = commands[$1] "\n   " $8
    for (i=9; i<=NF; i++) {
        commands[$1] = commands[$1] " " $i
    }

    if (!user_seen[$1]++) {
        user_order[++user_count] = $1
    }
    if ($5 ~ /^[0-9]{2}:[0-9]{2}$/) {
        split($5, t, ":")
        time_val = t[1] * 60 + t[2]
    } else {
        month = substr($5, 1, 3)
        day = substr($5, 4, 2)
        time_val = (index("JanFebMarAprMayJunJulAugSepOctNovDec", month) + 2) / 3 * 100 + day
    }

    if (earliest_time == "" || time_val < earliest_time) {
        earliest_time = time_val
        earliest_line = $0
    }
    if (latest_time == "" || time_val > latest_time) {
        latest_time = time_val
        latest_line = $0
    }
}
END {
    for (i=1; i<=user_count; i++) {
        user = user_order[i]
        print "User: " user commands[user]
        if (i < user_count) print ""
    }

    print "\nEarliest Start Time:"
    print earliest_line
    print "\nLatest Start Time:"
    print latest_line
}
