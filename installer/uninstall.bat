@echo off
setlocal enabledelayedexpansion
:: ============================================================
::  Dashboard APK uninstaller via ADB over WiFi
:: ============================================================

SET ADB_PATH=%~dp0resources\adb.exe
SET PACKAGE_NAME=com.came.parkare.dashboardapp

echo ============================================================
echo  DASHBOARD UNINSTALLER - WIFI CONNECTION
echo ============================================================
echo.

:: Check for an already connected device
echo Checking connected devices...
for /f "skip=1 tokens=1,2" %%a in ('"%ADB_PATH%" devices') do (
    if "%%b"=="device" (
        echo Device found: %%a
        set CONNECTED_DEVICE=%%a
    )
)

if defined CONNECTED_DEVICE (
    echo.
    echo [*] Device already connected: !CONNECTED_DEVICE!
    set /p USE_EXISTING="Use this device? (Y/N): "
    if /i "!USE_EXISTING!"=="Y" goto UNINSTALL
)

:: Step 1 - Connect
set /p CONN_ADDR="Enter the connection IP:PORT (e.g. 192.168.1.10:5555): "
echo.
echo [1/2] Connecting to the device...
"%ADB_PATH%" connect %CONN_ADDR%
if %errorlevel% neq 0 (
    echo ERROR connecting. Check the IP address and port.
    pause & exit /b 1
)

:UNINSTALL
:: Step 2 - Uninstall
echo.
echo [2/2] Uninstalling application...
"%ADB_PATH%" uninstall %PACKAGE_NAME%
if %errorlevel% neq 0 (
    echo ERROR uninstalling. Check that the application is installed.
    pause & exit /b 1
)

echo.
echo ============================================================
echo  Uninstall completed successfully!
echo ============================================================
pause