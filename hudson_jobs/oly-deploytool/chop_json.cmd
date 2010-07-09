@echo off
setlocal enabledelayedexpansion

rem call "%~dp0\00_setenv.cmd"

if defined WORKSPACE (
  set PROJECT_BASE=%WORKSPACE%\json
) else (
  set PROJECT_BASE=%TARGET_DIR%\json
)

if not exist "%PROJECT_BASE%" (
  echo Error: file not found %PROJECT_BASE%
  exit /b 1
)

rem --------------------------------------------------
rem   process deploy (all of JavaAP projects)
rem --------------------------------------------------
echo *** deployment begin at %DATE% %TIME% ***
echo.
call "%ANT_HOME%\bin\ant" "-Djson.project.dir=%PROJECT_BASE%" -f "%~dp0\build_json.xml" %*
echo.
echo *** deployment end at %DATE% %TIME% ***
if "%~1" == "" (
	echo *** clear temporary file begin at %DATE% %TIME% ***
	echo.
	echo cleaning...
	cd %PROJECT_BASE%
	C:\PROGRA~1\\SlikSvn\bin\svn st > C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q "M       " "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q "R       " "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q "?       " "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q "build_devl.xml" "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q "dist\soaIms001.war" "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q "dist" "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q "classes" "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	rpl -q " " "" C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list
	for /f "tokens=*" %%f in (C:\mywork\toolkit\oly-deploytool\tmp\json_diff.list) do c:\progra~1\GnuWin32\bin\rm -rf %%f
	echo.
	echo *** clear temporary file end at %DATE% %TIME% ***
	echo *** redepoly application using WLST ***
	C:\oracle\Middleware\wlserver_10.3\common\bin\wlst.cmd C:\mywork\toolkit\oly-deploytool\redeploy_json.py
)
endlocal