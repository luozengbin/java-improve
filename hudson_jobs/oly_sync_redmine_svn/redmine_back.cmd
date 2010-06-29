@echo off
setlocal enabledelayedexpansion
mysqldump -u root -pfreeadmin --single-transaction --master-data=2 --flush-logs --hex-blob bitnami_redmine > C:\mywork\toolkit\oly_sync_redmine_svn\redmine_back\redmine_dump_%BUILD_NUMBER%_%BUILD_ID%.sql
cd %WORKSPACE%
cp C:\mywork\toolkit\oly_sync_redmine_svn\redmine_back\redmine_dump_%BUILD_NUMBER%_%BUILD_ID%.sql .
endlocal