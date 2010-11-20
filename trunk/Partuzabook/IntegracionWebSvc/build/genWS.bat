call setenv.bat
set WS_PACKAGE=partuzabook.integracion.ws
C:\_Person\Dev\jboss-5.1.0.GA\bin\wsconsume -v -k -p %WS_PACKAGE% ..\src\partuzabook\integracion\ws\BusquedaService.wsdl
