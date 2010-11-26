@rem call setenv.bat
set WS_PACKAGE=partuzabook.integracion.ws.busqueda
set WSDL_FILE=..\src\partuzabook\integracion\ws\busqueda\BusquedaService.wsdl
C:\_Person\Dev\jboss-5.1.0.GA\bin\wsconsume -v -k -p %WS_PACKAGE% %WSDL_FILE%

@rem set WS_PACKAGE=partuzabook.integracion.ws.contenido
@rem set WSDL_FILE=..\src\partuzabook\integracion\ws\contenido\ContenidoService.wsdl
@rem C:\_Person\Dev\jboss-5.1.0.GA\bin\wsconsume -v -k -p %WS_PACKAGE% %WSDL_FILE%
