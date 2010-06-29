@echo off
setlocal enabledelayedexpansion

rem call "%~dp0\00_setenv.cmd"

if defined WORKSPACE (
  set PROJECT_BASE=%WORKSPACE%\cws
) else (
  set PROJECT_BASE=%TARGET_DIR%\cws
)

if not exist "%PROJECT_BASE%" (
  echo Error: file not found %PROJECT_BASE%
  exit /b 1
)

rem --------------------------------------------------
rem   process deploy (all of CustomWS projects)
rem --------------------------------------------------
echo *** deployment begin at %DATE% %TIME% ***
echo.
call "%ANT_HOME%\bin\ant" "-Dcustomws.project.dir=%PROJECT_BASE%" -f "%~dp0\build_cws.xml" %*
echo.
echo *** deployment end at %DATE% %TIME% ***
if "%~1" == "" (
	echo *** clear temporary file begin at %DATE% %TIME% ***
	echo.
	echo cleaning...
	echo %PROJECT_BASE%
	cd %PROJECT_BASE%
	C:\PROGRA~1\\SlikSvn\bin\svn st > C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list
	rpl -q "M       " "" C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list
	rpl -q "R       " "" C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list
	rpl -q "?       " "" C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list
	rpl -q "build_devl.xml" "" C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list
	rpl -q "dist\CustomWebService-Release.war" "" C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list
	rpl -q " " "" C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list
	for /f "tokens=*" %%f in (C:\mywork\toolkit\oly-deploytool\tmp\cws_diff.list) do c:\progra~1\GnuWin32\bin\rm -rf %%f
	echo.
	echo *** clear temporary file end at %DATE% %TIME% ***
	echo *** redepoly application using WLST ***
	C:\oracle\Middleware\wlserver_10.3\common\bin\wlst.cmd C:\mywork\toolkit\oly-deploytool\redeploy_csw.py
)
endlocal
