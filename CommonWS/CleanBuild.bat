REM Instalacion de Componentes
SET var=%cd%
call mvn clean install -U -f %var%\pom.xml
PAUSE