#!/bin/bash

if [ ! -d "data" ]; then
	mkdir data
fi

if [ ! -f "queries.log" ]; then
	touch data/queries.log
fi

while true; do
	echo "Enter one of the following actions or press CTRL-D to exit."
	echo "C - create a new course record"
	echo "R - read an existing course record"
	echo "U - update an existing course record"
	echo "D - delete an existing record"
	echo "E - update enrolled student count of existing course"
	echo "T - show total course count"

	if ! read -r usrChoice; then
		break
	fi

	usrChoice=${usrChoice^^}
	if [[ -z "$usrChoice" || ! "$usrChoice" =~ ^[CRUDET]$ ]]; then
		echo "ERROR: invalid option"
		continue
	fi

#	echo "Debug: urseChoice = $usrChoice"

	case "$usrChoice" in
		"C")
		./create.bash
		;;
		"R")
		./read.bash
		;;
		"U")
		./update.bash
		;;
		"D")
		./delete.bash
		;;
		"E")
		./enroll.bash
		;;
		"T")
		./total.bash
		;;
		*)
		echo "ERROR: Invalid Input"
		;;
	esac
done
