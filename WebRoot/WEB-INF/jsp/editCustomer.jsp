<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改客户信息页面</title>
	 <script type="text/javascript" src="${pageContext.request.contextPath }/js/ShowCalendar.js"></script>
	<script type="text/javascript">
		function makepre(){
			var pres = document.getElementsByName("pre");
			var preference = "";
			for(var i=0;i<pres.length;i++){
				var input = pres[i];
				if(input.checked==true){
					preference = preference + input.value + ",";
				}
			}
			preference = preference.substr(0,preference.length-1);
			
			var form = document.getElementById("form");
			var input = document.createElement("input");
			input.type = "hidden";
			input.name = "preference";
			input.value = preference;
			
			form.appendChild(input);
			return true;
		}
	</script>
  </head>
  
   <body style="text-align: center;">
   	<br/>
   	<form id="form" action="${pageContext.request.contextPath }/UpdataCustomerServlet" method="post" onsubmit="return makepre()">
   	<table border="1" width="50%">
   	<!-- 将id存入隐藏域中提交给servlet -->
   	<input  type="hidden" name="id" value="${c.id }"/>
   	<!-- 回显UpdataCustomerServlet提交过来要修改的数据 -->
   		<tr>
   			<td>客户姓名</td>
   			<td>
   				<input type="text" name="name" value="${c.name }" >
   			</td>
   		</tr>
   		
   		<tr>
   			<td>客户性别</td>
   			<td>
   				<c:forEach var="sex"  items="${sexs}">
					<input type="radio" name="sex" value="${sex}"${c.sex==sex?'checked':'' }>${sex}				
   				</c:forEach>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>生日</td>
   			<td>
   				<input type="text" name="birthday" onClick="showCalendar(this.id)" id="birthday" value="${c.birthday }" >
   			</td>
   		</tr>
   		
   		<tr>
   			<td>手机</td>
   			<td>
   				<input type="text" name="cellphone" value="${c.cellphone }">
   			</td>
   		</tr>
   		
   		<tr>
   			<td>邮箱</td>
   			<td>
   				<input type="text" name="email" value="${c.email }">
   			</td>
   		</tr>
   		
   		
   		<tr>
   			<td>爱好</td>
   			<td>
   				<c:forEach var="p" items="${preferences}">
   					<input type="checkbox" name="pre" value="${p }"${fn:contains(c.preference,p)?'checked':'' }>${p }
   				</c:forEach>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>客户类型</td>
   			<td>
   				<c:forEach var="type" items="${types}">
   					<input type="radio" name="type" value="${type }" ${c.type==type?'checked':''}>${type }
   				</c:forEach>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>客户备注</td>
   			<td>
   				<textarea rows="5" cols="60" name="description" >${c.description }</textarea>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   				<input type="reset" value="重置">
   			</td>
   			<td>
   				<input type="submit" value="确认修改">
   			</td>
   		</tr>
   	</table>
   	</form>
   	
  </body>
</html>
