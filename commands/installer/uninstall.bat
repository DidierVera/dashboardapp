@echo off
setlocal enabledelayedexpansion
:: ============================================================
::  Desinstalador Dashboard APK via ADB WiFi
:: ============================================================

SET ADB_PATH=%~dp0resources\adb.exe
SET PACKAGE_NAME=com.came.parkare.dashboardapp

echo ============================================================
echo  DESINSTALADOR DASHBOARD - CONFIGURACION WIFI
echo ============================================================
echo.

:: Verificar dispositivo ya conectado
echo Verificando dispositivos conectados...
for /f "skip=1 tokens=1,2" %%a in ('"%ADB_PATH%" devices') do (
    if "%%b"=="device" (
        echo Dispositivo encontrado: %%a
        set CONNECTED_DEVICE=%%a
    )
)

if defined CONNECTED_DEVICE (
    echo.
    echo [!] Dispositivo ya conectado: !CONNECTED_DEVICE!
    set /p USE_EXISTING="Usar este dispositivo? (S/N): "
    if /i "!USE_EXISTING!"=="S" goto UNINSTALL
)

:: Paso 1 - Connect
set /p CONN_ADDR="Ingresa IP:PUERTO de CONEXION (ej: 192.168.1.10:33943): "
echo.
echo [1/2] Conectando al dispositivo...
"%ADB_PATH%" connect %CONN_ADDR%
if %errorlevel% neq 0 (
    echo ERROR al conectar. Verifica IP y puerto.
    pause & exit /b 1
)

:UNINSTALL
:: Paso 2 - Desinstalar
echo.
echo [2/2] Desinstalando aplicacion...
"%ADB_PATH%" uninstall %PACKAGE_NAME%
if %errorlevel% neq 0 (
    echo ERROR al desinstalar. Verifica que la app este instalada.
    pause & exit /b 1
)

echo.
echo ============================================================
echo  Desinstalacion completada exitosamente!
echo ============================================================
pause