# Trabajo Final AutoWeb Galaxy

Proyecto final de automatización web desarrollado para el curso **Selenium Test Automation**. La suite automatiza flujos funcionales sobre Magento Testing Playground utilizando Java 21, Maven, Selenium WebDriver, TestNG, Page Object Model y Allure Report.

## Alumno

**Richard Paul Saucedo García**

## Aplicación bajo prueba

[Magento Testing Playground](https://github.com/lruizajax/magento-testing-playground)

El entorno Magento se obtiene desde el repositorio privado `https://github.com/lruizajax/magento-testing-playground`. Para acceder a ese repositorio se requiere autorización del propietario.

La aplicación se ejecuta mediante Docker Compose y queda disponible en:

```text
http://localhost:8081
```

## Alcance de las pruebas

El proyecto contiene 15 casos automatizados sobre Magento:

- 6 casos de registro.
- 5 casos de inicio de sesión.
- 4 casos de creación de órdenes.

## Tecnologías utilizadas

- Java 21
- Maven
- Selenium WebDriver 4.45.0
- TestNG 7.12.0
- Page Object Model
- Allure Report
- JavaFaker
- Docker Compose para el entorno Magento
- GitHub Actions para ejecución manual en CI

## Estructura principal

- `src/test/java/com/store/pages`: Page Objects de la aplicación Magento.
- `src/test/java/com/store/tests`: clases de prueba `RegisterTest`, `LoginTest` y `OrderTest`.
- `src/test/java/com/store/utils/BaseTest.java`: configuración base del navegador, URL de Magento y ciclo de vida del driver.
- `src/test/java/com/store/listeners/TestListener.java`: listener de TestNG para retry, evidencias y adjuntos de Allure.
- `src/test/java/com/store/utils/Retry.java`: configuración de reintentos para pruebas fallidas.
- `testng.xml`: suite principal de TestNG con grupos funcionales, regresión e integración.
- `.github/workflows/selenium.yml`: workflow manual de GitHub Actions para levantar Magento y ejecutar la suite.
- `pom.xml`: configuración Maven, dependencias y plugins del proyecto.

## Requisitos para ejecución local

- Java 21.
- Maven.
- Docker Desktop con Docker Compose.
- Google Chrome.
- Allure Commandline, solo si se desea abrir el reporte localmente.
- Acceso autorizado al repositorio privado de Magento.

## Preparación local de Magento

Para ejecutar la suite localmente, primero debe descargarse el repositorio privado de Magento Testing Playground con una cuenta autorizada. En la estructura de trabajo local se utiliza como repositorio separado del proyecto Selenium.

Desde el repositorio de Magento, el entorno se levanta con su archivo:

```bash
docker compose -f docker-compose.student.yml up -d
```

Cuando el entorno termina de iniciar, Magento debe responder en:

```text
http://localhost:8081
```

No se deben publicar credenciales, contraseñas ni valores sensibles del entorno Docker.

## Ejecución local de las pruebas

Con Magento disponible en `http://localhost:8081`, la suite se ejecuta desde el repositorio Selenium con:

```powershell
mvn clean test "-Dsurefire.suiteXmlFiles=testng.xml"
```

En ejecución local, Chrome se abre de forma visible. El modo headless se activa solamente cuando la variable de entorno `CI` tiene el valor `true`, comportamiento utilizado por GitHub Actions.

## Ejecución en GitHub Actions

El workflow se ejecuta manualmente:

1. Ingresar a la pestaña **Actions** del repositorio.
2. Seleccionar **Selenium Magento Tests**.
3. Seleccionar **Run workflow**.
4. Ejecutarlo sobre la rama `main`.

Actualmente `workflow_dispatch` es el único disparador configurado.

A nivel funcional, el workflow:

- Descarga el proyecto Selenium.
- Descarga de manera segura el repositorio privado de Magento.
- Configura Java 21 y caché Maven.
- Instala Allure Commandline.
- Levanta Magento con Docker Compose.
- Espera hasta que la aplicación esté disponible en `http://localhost:8081`.
- Ejecuta `testng.xml` con Chrome en modo headless.
- Genera y adjunta los resultados de las pruebas como artefactos descargables de la ejecución en GitHub Actions.
- Detiene el entorno Docker al finalizar.

## Configuración segura del repositorio privado

El workflow utiliza un Repository Secret ya configurado llamado:

```text
MAGENTO_REPO_TOKEN
```

Ese secret contiene un token autorizado para lectura del repositorio privado Magento. Un colaborador autorizado puede ejecutar manualmente el workflow desde GitHub Actions utilizando el secret existente, sin conocer ni volver a configurar su valor. Los secrets de GitHub no se almacenan en el código fuente ni se muestran en el README. El valor real del token nunca debe publicarse.

## Resultados y artefactos

Una ejecución satisfactoria validada obtuvo:

```text
Tests run: 15
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

GitHub Actions publica estos artefactos descargables:

- `allure-report`: reporte HTML generado por Allure.
- `allure-results`: resultados fuente utilizados para generar el reporte Allure.
- `surefire-reports`: reportes generados por Maven Surefire/TestNG.

Después de descargar y descomprimir `allure-report`, puede abrirse con:

```bash
allure open "ruta/al/allure-report"
```

## Consideraciones

- El repositorio Magento es privado.
- El token de acceso nunca debe publicarse.
- La ejecución en GitHub Actions utiliza Chrome en modo headless.
- Los artefactos se consultan desde la ejecución correspondiente en Actions.
