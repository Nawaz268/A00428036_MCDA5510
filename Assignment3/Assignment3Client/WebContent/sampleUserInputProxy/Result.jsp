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
        int ID_1idTemp  = Integer.parseInt(ID_1id);
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
        double unitPrice_4idTemp  = Double.parseDouble(unitPrice_4id);
        String quantity_5id=  request.getParameter("quantity24");
        int quantity_5idTemp  = Integer.parseInt(quantity_5id);
        String month_6id=  request.getParameter("month26");
        int month_6idTemp  = Integer.parseInt(month_6id);
        String year_7id=  request.getParameter("year28");
        int year_7idTemp  = Integer.parseInt(year_7id);
        java.lang.String createEntry13mtemp = sampleUserInputProxyid.createEntry(ID_1idTemp,nameOnCard_2idTemp,cardNumber_3idTemp,unitPrice_4idTemp,quantity_5idTemp,month_6idTemp,year_7idTemp);
if(createEntry13mtemp == null){
%>
<%=createEntry13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createEntry13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
case 30:
        gotMethod = true;
        String ID_8id=  request.getParameter("ID33");
            java.lang.String ID_8idTemp = null;
        if(!ID_8id.equals("")){
         ID_8idTemp  = ID_8id;
        }
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
            java.lang.String unitPrice_11idTemp = null;
        if(!unitPrice_11id.equals("")){
         unitPrice_11idTemp  = unitPrice_11id;
        }
        String quantity_12id=  request.getParameter("quantity41");
            java.lang.String quantity_12idTemp = null;
        if(!quantity_12id.equals("")){
         quantity_12idTemp  = quantity_12id;
        }
        String month_13id=  request.getParameter("month43");
            java.lang.String month_13idTemp = null;
        if(!month_13id.equals("")){
         month_13idTemp  = month_13id;
        }
        String year_14id=  request.getParameter("year45");
            java.lang.String year_14idTemp = null;
        if(!year_14id.equals("")){
         year_14idTemp  = year_14id;
        }
        java.lang.String updateEntry30mtemp = sampleUserInputProxyid.updateEntry(ID_8idTemp,nameOnCard_9idTemp,cardNumber_10idTemp,unitPrice_11idTemp,quantity_12idTemp,month_13idTemp,year_14idTemp);
if(updateEntry30mtemp == null){
%>
<%=updateEntry30mtemp %>
<%
}else{
        String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateEntry30mtemp));
        %>
        <%= tempResultreturnp31 %>
        <%
}
break;
case 47:
        gotMethod = true;
        String ID_15id=  request.getParameter("ID50");
        int ID_15idTemp  = Integer.parseInt(ID_15id);
        java.lang.String deleteEntry47mtemp = sampleUserInputProxyid.deleteEntry(ID_15idTemp);
if(deleteEntry47mtemp == null){
%>
<%=deleteEntry47mtemp %>
<%
}else{
        String tempResultreturnp48 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteEntry47mtemp));
        %>
        <%= tempResultreturnp48 %>
        <%
}
break;
case 52:
        gotMethod = true;
        String ID_16id=  request.getParameter("ID55");
        int ID_16idTemp  = Integer.parseInt(ID_16id);
        java.lang.String getEntry52mtemp = sampleUserInputProxyid.getEntry(ID_16idTemp);
if(getEntry52mtemp == null){
%>
<%=getEntry52mtemp %>
<%
}else{
        String tempResultreturnp53 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEntry52mtemp));
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