#!/bin/bash

# Check if at least one filename is provided
if [[ -z "$1" ]]; then
    echo "Usage: $0 <filename>"
    exit 1
fi

# Get the date
current_date=$(date +%Y-%m-%d)

# Loop through allfilenames
for input_file in "$@"; do
    if [[ -f "$input_file" ]]; then
        # Run sed using the script file
        sed -i -f assign2.sed "$input_file"
        echo "Processed $input_file"
    else
        echo "File $input_file does not exist."
    fi
done