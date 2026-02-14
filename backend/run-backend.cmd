@echo off
set "JAVA_HOME=C:\Program Files\Java\jdk-19"
cd /d "%~dp0"
echo Starting backend (MySQL - it342_carin_lab1)...
echo.
call mvnw.cmd spring-boot:run
pause
REM (pause keeps window open so you can see any errors)
