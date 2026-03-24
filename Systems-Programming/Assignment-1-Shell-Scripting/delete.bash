#!/bin/bash

read -p "Enter a course department code and number: " dept_code crse_num; dept_code=${dept_code^^}
crseFile="data/${dept_code}${crse_num}.crs"

if [[ ! -f "$crseFile" ]]; then
	echo "ERROR: course not found"
	exit 1
fi

{
read -r dept_name
read -r crse_name
} < "$crseFile"

rm -f "$crseFile"
echo "${crse_num} was successfully deleted."
echo "$(date) DELETED: $dept_code $crse_num $crse_name" >> data/queries.log
