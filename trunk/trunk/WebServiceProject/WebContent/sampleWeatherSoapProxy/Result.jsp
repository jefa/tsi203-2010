<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleWeatherSoapProxyid" scope="session" class="com.cdyne.ws.WeatherWS.WeatherSoapProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleWeatherSoapProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleWeatherSoapProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleWeatherSoapProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        com.cdyne.ws.WeatherWS.WeatherSoap getWeatherSoap10mtemp = sampleWeatherSoapProxyid.getWeatherSoap();
if(getWeatherSoap10mtemp == null){
%>
<%=getWeatherSoap10mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 15:
        gotMethod = true;
        com.cdyne.ws.WeatherWS.WeatherDescription[] getWeatherInformation15mtemp = sampleWeatherSoapProxyid.getWeatherInformation();
if(getWeatherInformation15mtemp == null){
%>
<%=getWeatherInformation15mtemp %>
<%
}else{
        String tempreturnp16 = null;
        if(getWeatherInformation15mtemp != null){
        java.util.List listreturnp16= java.util.Arrays.asList(getWeatherInformation15mtemp);
        tempreturnp16 = listreturnp16.toString();
        }
        %>
        <%=tempreturnp16%>
        <%
}
break;
case 18:
        gotMethod = true;
        String ZIP_1id=  request.getParameter("ZIP33");
            java.lang.String ZIP_1idTemp = null;
        if(!ZIP_1id.equals("")){
         ZIP_1idTemp  = ZIP_1id;
        }
        com.cdyne.ws.WeatherWS.ForecastReturn getCityForecastByZIP18mtemp = sampleWeatherSoapProxyid.getCityForecastByZIP(ZIP_1idTemp);
if(getCityForecastByZIP18mtemp == null){
%>
<%=getCityForecastByZIP18mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">weatherStationCity:</TD>
<TD>
<%
if(getCityForecastByZIP18mtemp != null){
java.lang.String typeweatherStationCity21 = getCityForecastByZIP18mtemp.getWeatherStationCity();
        String tempResultweatherStationCity21 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeweatherStationCity21));
        %>
        <%= tempResultweatherStationCity21 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">state:</TD>
<TD>
<%
if(getCityForecastByZIP18mtemp != null){
java.lang.String typestate23 = getCityForecastByZIP18mtemp.getState();
        String tempResultstate23 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typestate23));
        %>
        <%= tempResultstate23 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">responseText:</TD>
<TD>
<%
if(getCityForecastByZIP18mtemp != null){
java.lang.String typeresponseText25 = getCityForecastByZIP18mtemp.getResponseText();
        String tempResultresponseText25 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeresponseText25));
        %>
        <%= tempResultresponseText25 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">forecastResult:</TD>
<TD>
<%
if(getCityForecastByZIP18mtemp != null){
com.cdyne.ws.WeatherWS.Forecast[] typeforecastResult27 = getCityForecastByZIP18mtemp.getForecastResult();
        String tempforecastResult27 = null;
        if(typeforecastResult27 != null){
        java.util.List listforecastResult27= java.util.Arrays.asList(typeforecastResult27);
        tempforecastResult27 = listforecastResult27.toString();
        }
        %>
        <%=tempforecastResult27%>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">city:</TD>
<TD>
<%
if(getCityForecastByZIP18mtemp != null){
java.lang.String typecity29 = getCityForecastByZIP18mtemp.getCity();
        String tempResultcity29 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecity29));
        %>
        <%= tempResultcity29 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">success:</TD>
<TD>
<%
if(getCityForecastByZIP18mtemp != null){
%>
<%=getCityForecastByZIP18mtemp.isSuccess()
%><%}%>
</TD>
</TABLE>
<%
}
break;
case 35:
        gotMethod = true;
        String ZIP_2id=  request.getParameter("ZIP66");
            java.lang.String ZIP_2idTemp = null;
        if(!ZIP_2id.equals("")){
         ZIP_2idTemp  = ZIP_2id;
        }
        com.cdyne.ws.WeatherWS.WeatherReturn getCityWeatherByZIP35mtemp = sampleWeatherSoapProxyid.getCityWeatherByZIP(ZIP_2idTemp);
if(getCityWeatherByZIP35mtemp == null){
%>
<%=getCityWeatherByZIP35mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">visibility:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typevisibility38 = getCityWeatherByZIP35mtemp.getVisibility();
        String tempResultvisibility38 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typevisibility38));
        %>
        <%= tempResultvisibility38 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">state:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typestate40 = getCityWeatherByZIP35mtemp.getState();
        String tempResultstate40 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typestate40));
        %>
        <%= tempResultstate40 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">pressure:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typepressure42 = getCityWeatherByZIP35mtemp.getPressure();
        String tempResultpressure42 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typepressure42));
        %>
        <%= tempResultpressure42 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">wind:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typewind44 = getCityWeatherByZIP35mtemp.getWind();
        String tempResultwind44 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typewind44));
        %>
        <%= tempResultwind44 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">weatherStationCity:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typeweatherStationCity46 = getCityWeatherByZIP35mtemp.getWeatherStationCity();
        String tempResultweatherStationCity46 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeweatherStationCity46));
        %>
        <%= tempResultweatherStationCity46 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">city:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typecity48 = getCityWeatherByZIP35mtemp.getCity();
        String tempResultcity48 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecity48));
        %>
        <%= tempResultcity48 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">windChill:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typewindChill50 = getCityWeatherByZIP35mtemp.getWindChill();
        String tempResultwindChill50 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typewindChill50));
        %>
        <%= tempResultwindChill50 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">remarks:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typeremarks52 = getCityWeatherByZIP35mtemp.getRemarks();
        String tempResultremarks52 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeremarks52));
        %>
        <%= tempResultremarks52 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">weatherID:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
%>
<%=getCityWeatherByZIP35mtemp.getWeatherID()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">responseText:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typeresponseText56 = getCityWeatherByZIP35mtemp.getResponseText();
        String tempResultresponseText56 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeresponseText56));
        %>
        <%= tempResultresponseText56 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">temperature:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typetemperature58 = getCityWeatherByZIP35mtemp.getTemperature();
        String tempResulttemperature58 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typetemperature58));
        %>
        <%= tempResulttemperature58 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">description:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typedescription60 = getCityWeatherByZIP35mtemp.getDescription();
        String tempResultdescription60 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typedescription60));
        %>
        <%= tempResultdescription60 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">success:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
%>
<%=getCityWeatherByZIP35mtemp.isSuccess()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">relativeHumidity:</TD>
<TD>
<%
if(getCityWeatherByZIP35mtemp != null){
java.lang.String typerelativeHumidity64 = getCityWeatherByZIP35mtemp.getRelativeHumidity();
        String tempResultrelativeHumidity64 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typerelativeHumidity64));
        %>
        <%= tempResultrelativeHumidity64 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
}
} catch (Exception e) { 
%>
exception: <%= e %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>