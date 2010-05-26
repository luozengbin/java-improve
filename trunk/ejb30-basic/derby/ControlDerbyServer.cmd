@echo off
set path=%PATH%;C:\Program Files\Sun\JavaDB\bin

if "%1" == "start" (
	start startNetworkServer.bat
)else (
	stopNetworkServer.bat
)