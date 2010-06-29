@echo off
setlocal enabledelayedexpansion
cd C:\mywork\toolkit\oly_reloadLog
call %~dp0\reloadlog.bat BPELLog
c:\progra~1\GnuWin32\bin\nkf -s --overwrite "%WORKSPACE%"\log\bpel\tmp.log
c:\progra~1\GnuWin32\bin\cat "%WORKSPACE%"\log\bpel\tmp.log
exit /b %RESULT%
endlocal