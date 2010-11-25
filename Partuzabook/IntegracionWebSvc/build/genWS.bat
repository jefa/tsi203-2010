@rem call setenv.bat
@rem set WS_PACKAGE=partuzabook.integracion.ws.busqueda
@rem set WSDL_FILE=..\src\partuzabook\integracion\ws\busqueda\BusquedaService.wsdl
@rem C:\_Person\Dev\jboss-5.1.0.GA\bin\wsconsume -v -k -p %WS_PACKAGE% %WSDL_FILE%

set WS_PACKAGE=partuzabook.integracion.ws.contenido
set WSDL_FILE=..\src\partuzabook\integracion\ws\contenido\ContenidoService.wsdl
C:\_Person\Dev\jboss-5.1.0.GA\bin\wsconsume -v -k -p %WS_PACKAGE% %WSDL_FILE%
