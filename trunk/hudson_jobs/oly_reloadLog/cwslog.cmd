@echo off
setlocal enabledelayedexpansion
cd C:\mywork\toolkit\oly_reloadLog
call %~dp0\reloadlog.bat CwsLog
c:\progra~1\GnuWin32\bin\nkf -s --overwrite "%WORKSPACE%"\log\cws\CustomWebService-Release.log
c:\progra~1\GnuWin32\bin\cat "%WORKSPACE%"\log\cws\CustomWebService-Release.log
exit /b %RESULT%
endlocal