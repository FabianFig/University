awk -F : '{split($5,name," "); last=name[length(name)]; print last ":" $0}' "$1" | sort | awk -F : '{print substr($0, index($0,$2))}'
