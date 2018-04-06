<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<%@ page import="static entity.DemoCalenar.generateTimeFromLong" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> listProduct = (List<Product>) request.getAttribute("listProduct");
    int totalPage = (int)request.getAttribute("totalPage");
    int currentPage = (int)request.getAttribute("currentPage");
    int limit = (int) request.getAttribute("limit");
%>
<html>
<head>
    <title>List Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <ul>
        <%
            for(Product product: listProduct){
        %>
        <li>
            <img style="width: 150px; height: 150px;" src="<%=product.getPhotos()%>" alt="">
            <%= product.getName() %> ||
            <%= generateTimeFromLong(product.getId())%>
            <a href="/admin/product/edit?id=<%=product.getId()%>">Edit</a>&nbsp;&nbsp;
            <a href="/admin/product/delete?id=<%=product.getId()%>">Delete</a>
        </li>
        <%
            }
        %>

    </ul>
    <nav aria-label="pagination example">
        <ul class="pagination justify-content-center">
            <!--Previous button-->
            <% if(currentPage>1)
            {
            %>
            <li class="page-item disabled">
                <a class="page-link" href="/admin/product/list?page=<%= currentPage - 1%>&limit=<%= limit%>" tabindex="<%= currentPage<1?1:-1%>">Previous</a>
            </li>
            <%
                }
            %>
            <!--Numbers-->
            <li class="page-item"><a class="page-link" href="/admin/product/list?page=<%= currentPage -1 %>&limit=<%= limit%>"><%= currentPage -1%></a></li>
            <li class="page-item"><a class="page-link" href="/admin/product/list?page=<%= currentPage %>&limit=<%= limit%>"><%= currentPage%></a></li>
            <li class="page-item"><a class="page-link" href="/admin/product/list?page=<%= currentPage + 1%>&limit=<%= limit%>"><%= currentPage+1%></a></li>
            <% if(currentPage< totalPage)
            {
            %>
            <!--Next button-->
            <li class="page-item">
                <a class="page-link" href="/admin/product/list?page=<%= currentPage + 1 %>&limit=<%= limit%>" tabindex="<%= currentPage<totalPage ?1:-1%>">Next</a>
            </li>
            <%
                }
            %>
        </ul>
    </nav>

</body>
</html>
