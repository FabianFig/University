#!/bin/bash

ttlFiles=$(ls data/*.crs 2>/dev/null | wc -l)

if [[ "$ttlFiles" -eq 0 ]]; then
	echo "Total course records: 0"
else
	echo "Total course records: $ttlFiles"
fi
