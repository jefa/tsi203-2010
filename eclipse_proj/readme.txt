1.- Apuntar el workspace al directorio "eclipse_proj"

2.- Variables requeridas en Eclipse
Use variables para las dependencias asi podemos usar todos las rutas que tenemos hoy en dia compartiendo los proyectos. Deberia funcionar para el que usa linux tambien.

Por ejemplo las mias son:
JBOSS_HOME = C:/_Person/Dev/jboss-5.1.0.GA
ECLIPSE_HOME = C:\_Person\Dev\eclipse-jee

Agregarglas en Window/Preferences:
	Java/Build Path/Classpath Variables
	
Si agregan bibliotecas, en el Build Path del proyecto tienen que clickear en "Add Variable" en vez de en "Add Library" o "Add Jar". Luego le dan en "Extend" y buscan el jar o lo que sea a partir de la variable. Espero se entienda :)

3.- Acomodar los facets
Esto es el lio de matchear lo que hay en los facets de cada proyecto a los servers que tengan configurados localmente.
Creo que se debe poder deshabilitar el uso de facets. Despues lo vemos.

4.- Deploy
Hay algunas cosas que deberiamos copiar con un Ant o algo. Eso lo puse en el directorio "deploy":

.- destination services (donde se registra la cola de mensajes)
.- login-config (donde se crea el authentication realm...yo lo necesite para postgres en linux y es la configracion final u oficial)
.- postgres data source
.- axis1.4: esto es para correr los webservices. hay que copiarlo en "deploy" de jboss.
