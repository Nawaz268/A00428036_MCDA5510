<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleUserInputProxyid" scope="session" class="com.mcds5510.service.UserInputProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleUserInputProxyid.setEndpoint(request.getParameter("endpoint"));
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
        java.lang.String getEndpoint2mtemp = sampleUserInputProxyid.getEndpoint();
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
        sampleUserInputProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        com.mcds5510.service.UserInput getUserInput10mtemp = sampleUserInputProxyid.getUserInput();
if(getUserInput10mtemp == null){
%>
<%=getUserInput10mtemp %>
<%
}else{
        if(getUserInput10mtemp!= null){
        String tempreturnp11 = getUserInput10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String ID_1id=  request.getParameter("ID16");
            java.lang.String ID_1idTemp = null;
        if(!ID_1id.equals("")){
         ID_1idTemp  = ID_1id;
        }
        String nameOnCard_2id=  request.getParameter("nameOnCard18");
            java.lang.String nameOnCard_2idTemp = null;
        if(!nameOnCard_2id.equals("")){
         nameOnCard_2idTemp  = nameOnCard_2id;
        }
        String cardNumber_3id=  request.getParameter("cardNumber20");
            java.lang.String cardNumber_3idTemp = null;
        if(!cardNumber_3id.equals("")){
         cardNumber_3idTemp  = cardNumber_3id;
        }
        String unitPrice_4id=  request.getParameter("unitPrice22");
            java.lang.String unitPrice_4idTemp = null;
        if(!unitPrice_4id.equals("")){
         unitPrice_4idTemp  = unitPrice_4id;
        }
        String quantity_5id=  request.getParameter("quantity24");
            java.lang.String quantity_5idTemp = null;
        if(!quantity_5id.equals("")){
         quantity_5idTemp  = quantity_5id;
        }
        String month_6id=  request.getParameter("month26");
            java.lang.String month_6idTemp = null;
        if(!month_6id.equals("")){
         month_6idTemp  = month_6id;
        }
        String year_7id=  request.getParameter("year28");
            java.lang.String year_7idTemp = null;
        if(!year_7id.equals("")){
         year_7idTemp  = year_7id;
        }
        java.lang.String updateEntry13mtemp = sampleUserInputProxyid.updateEntry(ID_1idTemp,nameOnCard_2idTemp,cardNumber_3idTemp,unitPrice_4idTemp,quantity_5idTemp,month_6idTemp,year_7idTemp);
if(updateEntry13mtemp == null){
%>
<%=updateEntry13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateEntry13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
case 30:
        gotMethod = true;
        String ID_8id=  request.getParameter("ID33");
        int ID_8idTemp  = Integer.parseInt(ID_8id);
        String nameOnCard_9id=  request.getParameter("nameOnCard35");
            java.lang.String nameOnCard_9idTemp = null;
        if(!nameOnCard_9id.equals("")){
         nameOnCard_9idTemp  = nameOnCard_9id;
        }
        String cardNumber_10id=  request.getParameter("cardNumber37");
            java.lang.String cardNumber_10idTemp = null;
        if(!cardNumber_10id.equals("")){
         cardNumber_10idTemp  = cardNumber_10id;
        }
        String unitPrice_11id=  request.getParameter("unitPrice39");
        double unitPrice_11idTemp  = Double.parseDouble(unitPrice_11id);
        String quantity_12id=  request.getParameter("quantity41");
        int quantity_12idTemp  = Integer.parseInt(quantity_12id);
        String month_13id=  request.getParameter("month43");
        int month_13idTemp  = Integer.parseInt(month_13id);
        String year_14id=  request.getParameter("year45");
        int year_14idTemp  = Integer.parseInt(year_14id);
        java.lang.String createEntry30mtemp = sampleUserInputProxyid.createEntry(ID_8idTemp,nameOnCard_9idTemp,cardNumber_10idTemp,unitPrice_11idTemp,quantity_12idTemp,month_13idTemp,year_14idTemp);
if(createEntry30mtemp == null){
%>
<%=createEntry30mtemp %>
<%
}else{
        String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createEntry30mtemp));
        %>
        <%= tempResultreturnp31 %>
        <%
}
break;
case 47:
        gotMethod = true;
        String ID_15id=  request.getParameter("ID50");
        int ID_15idTemp  = Integer.parseInt(ID_15id);
        java.lang.String getEntry47mtemp = sampleUserInputProxyid.getEntry(ID_15idTemp);
if(getEntry47mtemp == null){
%>
<%=getEntry47mtemp %>
<%
}else{
        String tempResultreturnp48 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEntry47mtemp));
        %>
        <%= tempResultreturnp48 %>
        <%
}
break;
case 52:
        gotMethod = true;
        String ID_16id=  request.getParameter("ID55");
        int ID_16idTemp  = Integer.parseInt(ID_16id);
        java.lang.String deleteEntry52mtemp = sampleUserInputProxyid.deleteEntry(ID_16idTemp);
if(deleteEntry52mtemp == null){
%>
<%=deleteEntry52mtemp %>
<%
}else{
        String tempResultreturnp53 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteEntry52mtemp));
        %>
        <%= tempResultreturnp53 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
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