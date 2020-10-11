<%@ page import="com.sikorakam.medicalcheckupsapp.dao.entity.Client" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head th:insert="fragments.html :: headerfiles">

    <meta charset="ISO-8859-1">
    <title>Home JSP</title>
</head>
<header th:insert="fragments.html :: nav"></header>
<body>
<%
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Client client = (Client) authentication.getPrincipal();
    Long clientId = client.getId();
%>
<a href="/api/clients/id=<% clientId %>"
</body>
</html>