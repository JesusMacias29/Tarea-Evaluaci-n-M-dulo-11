# 🚀 Desarrollo y Distribución de una App JavaFX (Windows)

👤 **Autor:** JESÚS MACÍAS  

---

## 📌 ¿De qué va este proyecto?
Este repositorio contiene una aplicación de escritorio hecha con **JavaFX**, organizada con **Maven**, y preparada para entregarse como si fuera un caso real de empresa: lista para instalar en **Windows**.

La idea es completar el ciclo completo de distribución:

✅ Generar un **JAR ejecutable**  
✅ Convertirlo en un **.exe** con **Launch4j** (sin consola)  
✅ Crear un **instalador para Windows** con **Inno Setup** (con accesos directos y desinstalación)

---

## 🛠️ Herramientas y tecnologías usadas
- **Java** (recomendado JDK 17 o superior)
- **JavaFX**
- **Maven**
- **Launch4j**
- **Inno Setup**

---

## 📂 Estructura del proyecto
```bash
📁 proyecto/
 ├── 📁 src/                     # Código fuente
 ├── 📁 resources/                # Recursos (iconos, imágenes, etc.)
 ├── 📁 target/                   # Archivos compilados por Maven
 ├── 📁 dist/                     # Entrega final (JAR, EXE, instalador)
 │    ├── app.jar
 │    ├── app.exe
 │    ├── installer.exe
 │    └── jre/                    # Java embebido (si aplica)
 ├── pom.xml
 └── README.md

---
✅ Requisitos
Para compilar el proyecto desde cero necesitas:

Java JDK instalado

Maven

Windows recomendado (sobre todo para generar el .exe y el instalador)
---
1️⃣ Generación del JAR ejecutable (Maven)
🔧 Compilar el proyecto
Desde la carpeta raíz del proyecto:

bash
Copiar código
mvn clean package
Esto genera el .jar dentro de:

bash
Copiar código
/target/


▶️ Probar el JAR (IMPORTANTE)
Antes de seguir con el exe, hay que comprobar que el jar funciona bien:

bash
Copiar código
java -jar target/NOMBRE-DEL-JAR.jar
✅ Si se abre la interfaz de JavaFX sin errores, perfecto.
---
2️⃣ Crear el .exe con Launch4j
🎯 Objetivo
Convertir el .jar en un .exe que:

✅ abra la app directamente
✅ no muestre consola
✅ funcione incluso si el PC no tiene Java instalado (con JRE incluida)

⚙️ Configuración usada en Launch4j
En Launch4j se configuró:

Output file: app.exe

Jar: app.jar

Header type: GUI (para que no aparezca consola)

Icon: icono.ico (si se usa icono personalizado)

Bundled JRE path: jre/ (para que funcione sin Java instalado)

📌 El resultado se guarda en:

bash
Copiar código
/dist/app.exe
✅ Prueba del ejecutable
Se comprobó que:

El .exe abre la aplicación correctamente

No se muestra consola

Funciona en un equipo sin Java instalado (si se incluye la carpeta jre/)
---
3️⃣ Crear instalador con Inno Setup
🎯 Objetivo del instalador
El instalador final debe:

✅ instalar la app en una ruta correcta (ej: Program Files)
✅ crear accesos directos (Escritorio + Menú Inicio)
✅ permitir desinstalar sin problemas
✅ dejar el sistema limpio

📦 Resultado del instalador
El instalador final generado es:

bash
Copiar código
/dist/installer.exe
Incluye:

carpeta de instalación propia

accesos directos automáticos

opción de desinstalar desde Windows

personalización (nombre, icono, textos, etc.)

🧪 Pruebas finales hechas
Antes de entregar, se probó todo el proceso:

✔ Instalación
Se instala sin errores

Se crean accesos directos

✔ Ejecución
Se abre correctamente desde los accesos directos

No falla al iniciarse

✔ Desinstalación
Se elimina completamente desde Windows

No deja restos importantes
---
📄 Documentación del proceso
Para la entrega se incluye un documento/presentación donde se explica:

📌 pasos realizados
📌 herramientas usadas
📌 errores típicos encontrados y cómo se solucionaron

📤 
La entrega contiene:

✅ Instalador final .exe
✅ Carpeta con archivos principales (JAR, EXE, recursos y JRE si aplica)
✅ Presentación PowerPoint (o similar) con los pasos
✅ Enlace al repositorio Git

⚠️ Problemas típicos (por si pasa)
❗ El JAR no abre JavaFX
➡️ Revisar dependencias en pom.xml y que se empaquete bien.

❗ Sale consola al abrir el EXE
➡️ En Launch4j usar Header type = GUI.

❗ No funciona en PCs sin Java
➡️ Incluir carpeta jre/ y poner bien el Bundled JRE path.

❗ El instalador no copia todo
➡️ Revisar el script de Inno Setup y añadir recursos/archivos necesarios.
