<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 3/16/18
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String uploadUrl =  BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/admin/product/add");
%>
<html>
<head>
    <title>Product Form</title>
</head>
<body>
    <h1>Product Form</h1>
    <form action="<%=uploadUrl%>" method="POST" enctype="multipart/form-data">
        Name <input type="text" name="username">
        <br>
        Unit Price <input type="text" name="unitPrice">
        <br>
        Image <input type="file" name="myImage">
        <br>
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </form>
</body>
</html>
