<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列出所有客户</title>
  </head>
  
  <body style="text-align: center;">
    
    <table frame="border" width="85%">
    	<tr>
    		<td>客户姓名</td>
    		<td>性别</td>
    		<td>生日</td>
    		<td>手机</td>
    		<td>邮箱</td>
    		<td>爱好</td>
    		<td>类型</td>
    		<td>备注</td>
    		<td>操作</td>
    	<tr>
    	
    	<c:forEach var="c" items="${requestScope.pagebean.list}">
    		<tr>
	    		<td>${c.name }</td>
	    		<td>${c.sex }</td>
	    		<td>${c.birthday }</td>
	    		<td>${c.cellphone }</td>
	    		<td>${c.email }</td>
	    		<td>${c.preference }</td>
	    		<td>${c.type }</td>
	    		<td>${c.description }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/UpdataCustomerServlet?id=${c.id}">修改</a>
	    			<a href="javascript:void(0)" onclick="del('${c.id}')">删除</a>
	    		</td>
    		<tr>
    	</c:forEach>
    </table>
     <script type="text/javascript">
    	function gotopage(currentpage){
	    		var pagesize = document.getElementById("pagesize").value;
	    		window.location.href = '${pageContext.request.contextPath}/ListCustomerServlet?currentpage=' + currentpage + '&pagesize=' + pagesize;
    	}
    	
    	function changesize(pagesize,oldvalue){
    		if(pagesize<0||pagesize!=parseInt(pagesize)){
    			alert("请输入合法的值！");
    			document.getElementById("pagesize").value=oldvaue;
    		}else{
    			window.location.href='${pageContext.request.contextPath}/ListCustomerServlet?pagesize='+pagesize;
    		}
    	}
    	
    	function del(id){
    		if(window.confirm("您确认删除吗?")){
    			location.href='${pageContext.request.contextPath}/DelCustomerServlet?id='+ id;
    		}
    	}
    </script>
    
    共[${pagebean.totalrecord }]条记录,
    每页<input type="text" id="pagesize" value="${pagebean.pagesize }" onchange="changesize(this.value,${pagebean.pagesize })" style="width: 30px" maxlength="2">条,
    共[${pagebean.totalpage }]页,
    当前[${pagebean.currentpage }]页
    &nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0)" onclick="gotopage(${pagebean.previouspage })">上一页</a>
	    
	    <c:forEach var="pagenum" items="${pagebean.pagebar}">
	    	<c:if test="${pagenum==pagebean.currentpage}">
	    		<font color='red'>${pagenum }</font>
	    	</c:if>
	    	
	    	<c:if test="${pagenum!=pagebean.currentpage}">
	    		<a href="javascript:void(0)" onclick="gotopage(${pagenum })">${pagenum }</a>
	    	</c:if>
	    </c:forEach>
    
    <a href="javascript:void(0)" onclick="gotopage(${pagebean.nextpage })">下一页</a>
    <input type="text" id="pagenum" style="width: 30px"/>
    <input type="button" value="GO" onclick="gotopage(document.getElementById('pagenum').value)" />
  </body>
</html>
