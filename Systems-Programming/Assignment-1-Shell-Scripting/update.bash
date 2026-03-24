#!/bin/bash

read -p "Enter a department code and course number: " dept_code crse_num
dept_code=${dept_code^^}
crseFile="data/${dept_code}${crse_num}.crs"

if [[ ! -f "$crseFile" ]]; then
	echo "ERROR: course not found"
	exit 1
fi

{
read -r dept_code dept_name
read -r crse_name
read -r crse_sched crse_start crse_end
read -r crse_hrs
read -r crse_enrol
} < "${crseFile}"

echo "Leave blank to keep original value"
read -p "Enter department code: " newDept_code
read -p "Enter department name: " newDept_name
read -p "Enter course number: " newCrse_num
read -p "Enter course name: " newCrse_name
read -p "Enter course meeting days: " newCrse_sched
read -p "Enter course start date: " newCrse_start
read -p "Enter course end date: " newCrse_end
read -p "Enter course credit hours: " newCrse_hrs
read -p "Enter amount of enrolled students: " newCrse_enrol

}
echo "${newDept_code:-$dept_code} ${newDept_name:-$dept_name}"
echo "${newCrse_name:-$crse_name}"
echo "${newCrse_sched:-$crse_sched} ${newCrse_start:-$crse_start} ${newCrse_end:-$crse_end}"
echo "${newCrse_hrs:-$crse_hrs}"
echo "${newCrse_enrol:-$crse_enrol}"
} > "$crseFile"

echo "$(date) UPDATED: $dept_code $crse_num $crse_name" >> data/queries.log
echo "Course $dept_code $crse_num updated successfully."
