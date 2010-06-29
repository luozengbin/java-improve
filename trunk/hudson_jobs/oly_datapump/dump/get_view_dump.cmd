@echo off
setlocal enabledelayedexpansion
echo *** process begin at %DATE% %TIME% ***
echo.
expdp system/Manager123@swld01.olympus.co.jp/swl.olympus.co.jp CONTENT=METADATA_ONLY INCLUDE=VIEW DUMPFILE=produce_view_def.dmp LOGFILE=produce_view_def.log SCHEMAS=PRODUCE
cd %WORKSPACE%
ftp -s:%~dp0\ftp_get_view_def_cmd.txt
echo.
echo *** process end at %DATE% %TIME% ***
endlocal