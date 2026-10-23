Equipo: TQM Enoc
Integrantes:
- Correa Merino Fernanda
- Pérez Hernández Francisco Gadiel

Recomendación general: Clonar todo el contenido del repositorio en la ruta de fichero de su elección, de esa forma tendrá todos los archivos necesarios en su dispositivo y en las rutas definidas con las que podrá hacer funcionar los comandos que se presentarán a contunuación. Esto hará funcionar tanto los casos de Java como los de Python


*+Para Python**

1. Cómo ejecutar las pruebas:
   Antes de cualquier cosa hay que asegurarse de que el dispositivo tiene instalado el programa Pytest, que se encarga de ejecutar los tests para Python. Para hacerlo
   escribe en la terminal el comando "pytest --version" (sin las comillas) y aparecerá un mensaje que indicará la versión de Pytest instalada en el dispositivo o, en
   su defecto, que el programa no se encuentra instalado. Para instalar Pytest se debe escribir el comando "python -m pip install pytest" y esperar a que termine el
   proceso de instalación en la terminal, una vez que esto suceda se debe colocar el comando "pytest --version" (sin las comillas) de nuevo para verificar la
   instalación.
   Con Pytest ya instalado dirígete a la ruta donde esté guardada la carpeta correspondiente al repositorio clonado, dicha carpeta tendrá el nombre de practica4. Una
   vez ahí ejecuta el comando "cd practica4/python" (sin las comillas) en tu terminal. Ya estando dentro de la carpeta correspondiente al código de Python ejecuta el
   comando "python -m pip install -r requirements . txt" (sin las comillas externas) para preparar a Pytest, después de eso escribe el comando "pytest -v" (sin las
   comillas) para que Pytest pueda buscar los tests y ejecutarlos. A continuación aparecerán los resultados de las pruebas en la pantalla donde se indicará cuáles
   pasaron y cuáles fallaron junto con una breve explicación del por qué
   
   
**Para Java**

1. Cómo ejecutar las pruebas:
   Similar al caso de Python, primero se debe verificar que Java y Maven (el encargado de la ejecución de las pruebas) estén instalados con los comandos "java -
   version" (sin las comillas) y "mvn - version" (sin las comillas) respectivamente.
   Tras verificar esto dirígete a la ruta donde la carpeta del repositorio está guardada, después ejecuta "cd java" (sin las comillas) para dirigirte a la carpeta que
   corresponde al código en Java. Posteriormente ejecuta "mvn test" (sin las comillas) para que Maven busque y ejecute las pruebas definidas. En la terminal se
   imprimirán los resultados de las pruebas de forma similar a los casos de Python
