@echo off
echo ========================================
echo Configurando Git y vinculando a GitHub
echo ========================================
echo.

REM Inicializar repositorio git si no existe
if not exist .git (
    echo Inicializando repositorio Git...
    git init
    echo.
) else (
    echo Repositorio Git ya existe.
    echo.
)

REM Agregar todos los archivos
echo Agregando archivos al staging area...
git add .
echo.

REM Hacer el primer commit
echo Haciendo commit inicial...
git commit -m "Initial commit - TP3 Simulacro Todo List"
echo.

REM Cambiar la rama a main si es necesario
echo Cambiando a rama main...
git branch -M main
echo.

REM Agregar el remote de GitHub
echo Configurando remote de GitHub...
git remote remove origin 2>nul
git remote add origin https://github.com/alejursza/TP3-Simulacro-Todo-List.git
echo.

REM Mostrar la configuración
echo Configuración actual:
git remote -v
echo.

echo ========================================
echo Configuración completada!
echo ========================================
echo.
echo Para subir los cambios a GitHub, ejecuta:
echo     git push -u origin main
echo.
echo Si el repositorio ya tiene contenido, usa:
echo     git pull origin main --allow-unrelated-histories
echo     git push -u origin main
echo.
pause

