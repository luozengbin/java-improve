@echo off
pushd "%~1" 2>&1 > nul
grep -iEo "<service.*? guid=\"*([0-9A-F]*)\"* " *.esbsvc | sed -e "s/.* guid=\"*\([0-9A-F]*\)\"* .*/\1/ig"
popd  2>&1 > nul
