@echo off
setlocal enabledelayedexpansion

call "%~dp0\00_setenv.cmd"

set DEPLOY_LOG=%LOG_DIR%\%TODAY%_xpath.log
if not exist "%LOG_DIR%" md "%LOG_DIR%"

"%~dp0\chop_xpath.cmd" %* | tee "%DEPLOY_LOG%"

endlocal
