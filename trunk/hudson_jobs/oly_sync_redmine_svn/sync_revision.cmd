@echo off
setlocal enabledelayedexpansion
call C:\PROGRA~1\BitNam~1\use_redmine.bat
cd C:\PROGRA~1\BitNam~1\apps\redmine
ruby script/runner "Repository.fetch_changesets" -e production
endlocal