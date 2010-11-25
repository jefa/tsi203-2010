
set WS_PACKAGE=partuzabook.integracion.ws.productora_web
set WSDL_FILE=..\src\partuzabook\integracion\ws\productora_web\IntegracionWSService.wsdl
C:\_Person\Dev\jboss-5.1.0.GA\bin\wsconsume -v -k -p %WS_PACKAGE% %WSDL_FILE%
