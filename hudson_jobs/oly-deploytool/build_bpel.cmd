@echo off
setlocal enabledelayedexpansion

if "%~1" == "" (
  echo "Usage: %0 {BPEL-Cmp|BPEL-Req} [clean]"
  exit /b 1
)

call "%~dp0\00_setenv.cmd"

set DEPLOY_LOG=%LOG_DIR%\%TODAY%_bpel.log
if not exist "%LOG_DIR%" md "%LOG_DIR%"

"%~dp0\chop_bpel.cmd" %* | tee %DEPLOY_LOG%

endlocal
