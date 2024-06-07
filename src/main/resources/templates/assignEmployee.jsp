<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employe Affectation</title>
</head>
<body>
<h1>Employe Affectation</h1>
<a href="/employees">employees</a> | <a href="/">Back to home</a>
<form action="/assign" method="post">
    <label for="employeeId">Employee Name:</label>
    <select name="employeeId" id="employeeId">
        <c:forEach var="employee" items="${employees}">
            <option value="${employee.id}">${employee.name}</option>
        </c:forEach>
    </select><br>
    <label for="projectId">Project Name:</label>
    <select name="projectId" id="projectId">
        <c:forEach var="project" items="${projects}">
            <option value="${project.id}">${project.name}</option>
        </c:forEach>
    </select><br>
    <label for="implication">Implication:</label>
    <select name="implication" id="implication">
        <option value="10">10%</option>
        <option value="20">20%</option>
        <option value="30">30%</option>
    </select><br>
    <button type="submit">Affecter Project</button>
</form>
</body>
</html>
