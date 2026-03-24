#!/bin/bash

if [ ! -d "data" ]; then
	echo "ERROR: data directory not found"
	exit 1
fi

echo "Enter a Department code and course number: (e.g., cs 3423)"
#read dept_code crse_num
if ! read -r dept_code crse_num; then
	exit 1
fi

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
read -r crse_size
} < "$crseFile"

echo "Course department: $dept_code $dept_name"
echo "Course number: $crse_num"
echo "Course name: $crse_name"
echo "Scheduled days: $crse_sched"
echo "Course start: $crse_start"
echo "Course end: $crse_end"
echo "Credit hours: $crse_hrs"
echo "Enrolled Students: $crse_size"

echo "$(date) READ: $dept_code $crse_num" >> data/queries.log

