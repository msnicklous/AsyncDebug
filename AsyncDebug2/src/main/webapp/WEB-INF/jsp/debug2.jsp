<%@ page session="false" %>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<h5>Async Debug 2 JSP</h5>
<p>Dispatch type: <%=request.getDispatcherType() %></p>
<p>Context path: <%=request.getServletContext().getContextPath() %></p>
<hr>
