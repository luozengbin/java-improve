@echo off
setlocal enabledelayedexpansion
cd C:\mywork\toolkit\oly_reloadLog
call %~dp0\reloadlog.bat JSONLog
c:\progra~1\GnuWin32\bin\nkf -s --overwrite "%WORKSPACE%"\log\json\soaIms001.log
c:\progra~1\GnuWin32\bin\cat "%WORKSPACE%"\log\json\soaIms001.log
exit /b %RESULT%
endlocal