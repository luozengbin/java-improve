@echo off
setlocal enabledelayedexpansion
call C:\PROGRA~1\BitNam~1\use_redmine.bat
cd C:\PROGRA~1\BitNam~1\apps\redmine
ruby script/runner C:\mywork\toolkit\oly_sync_redmine_svn\update_svn_comment.rb -e production
endlocal