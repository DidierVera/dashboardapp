@echo off
setlocal enabledelayedexpansion
:: ============================================================
::  Actualizador Dashboard APK via ADB WiFi
:: ============================================================

SET ADB_PATH=%~dp0resources\adb.exe
SET APK_PATH=%~dp0dashboard-release.apk

echo ============================================================
echo  ACTUALIZADOR DASHBOARD - CONFIGURACION WIFI
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
    if /i "!USE_EXISTING!"=="S" goto UPDATE
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

:UPDATE
:: Paso 2 - Instalar nueva version
echo.
echo [2/2] Instalando nueva version...
"%ADB_PATH%" install -t -r "%APK_PATH%"
if %errorlevel% neq 0 (
    echo ERROR al instalar el APK.
    pause & exit /b 1
)

echo.
echo ============================================================
echo  Actualizacion completada exitosamente!
echo ============================================================
pause