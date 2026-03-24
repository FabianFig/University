#!/bin/bash

read -p "Enter a course department code and number (e.g., cs 3423): " dept_code crse_num
dept_code=${dept_code^^}
crseFile="data/${dept_code}${crse_num}.crs"

if [[ ! -f "$crseFile" ]]; then
	echo "ERROR: Course not found"
	./enroll.bash
fi

read -p "Enter an enrollment change amount: " enrol_chng

{
read -r dept_code dept_name
read -r crse_name
read -r crse_sched crse_start crse_end
read -r crse_hrs
read -r curr_enrol
} < "${crseFile}"

upd_enrol=$((curr_enrol + enrol_chng))

{
echo "$dept_name $dept_name"
echo "$crse_name"
echo "$crse_sched $crse_start $crse_end"
echo "$crse_hrs"
echo "$upd_enrol"
} > "$crseFile"

echo "$(date) ENROLLMENT: $dept_code $crse_num $crse_name changed by $enrol_chng" >> data/queries.log


