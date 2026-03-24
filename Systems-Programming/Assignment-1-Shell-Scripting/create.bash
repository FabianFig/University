#!/bin/bash

if [ ! -d "data" ]; then
	mkdir data
fi

read -p "Enter department code: " dept_code; dept_code=${dept_code^^}
read -p "Enter department name: " dept_name
read -p "Enter course number: " crse_num
read -p "Enter course name: " crse_name
read -p "Enter course schedule: " crse_sched
read -p "Enter course start date: " crse_start
read -p "Enter course end date: " crse_end
read -p "Enter course credit hours: " crse_hrs
read -p "Enter initial course enrollment: " crse_enrol

crseFile="data/$dept_code$crse_num.crs"

if [[ -f "$crseFile" ]]; then
	echo "ERROR: course already exists"
	exit 1;
fi

{
echo "$dept_code $dept_name"
echo "$crse_name"
echo "$crse_sched $crse_start $crse_end"
echo "$crse_hrs"
echo "$crse_enrol"
} > "$crseFile"

echo "$(date) CREATED: $dept_code $crse_num $crse_name" >> data/queries.log
echo "Course $dept_code $crse_num created successfully."
