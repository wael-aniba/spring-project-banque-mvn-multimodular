<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>

<head>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style1.css" />
<title>banque</title>
</head>

<body>

	<div>
		<table>
			<tr>
				<td>
					<img width="155px" height="60px" alt="logo" src="<%=request.getContextPath()%>/resources/img/logo.png" />
				</td>
				<td>&nbsp;</td>
				<td class="siteTilte">
					| BANQUE DE MERDE
				</td>
			</tr>
		</table>
		
		
	</div>
	<div>
		<f:form method="post" action="chargerCompte" modelAttribute="banqueForm">
			<table>
				<tr>
					<td>Code:</td>
					<td><f:input path="code" /></td>
					<td><input type="submit" value="OK"></td>
				</tr>
				<tr class="error">
					<td colspan="3"><f:errors path="code" /></td>
				</tr>
			</table>
		</f:form>
	</div>

	<c:if test="${not empty banqueForm.compte}">
		<div>
			<table>
				<tr>
					<td>Solde:</td>
					<td>${banqueForm.compte.solde}</td>
				</tr>
				<tr>
					<td>Date Création:</td>
					<td>${banqueForm.compte.dateCreation}</td>
				</tr>
				<tr>
					<td>Type:</td>
					<td>${banqueForm.typeCompte}</td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${banqueForm.typeCompte.equals('Courant')}">
							<td>Decouvert:</td>
							<td>${banqueForm.compte.decouvert}</td>
						</c:when>
						<c:otherwise>
							<td>Taux:</td>
							<td>${banqueForm.compte.taux}</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<td>Client:</td>
					<td>${banqueForm.compte.client.nomClient}</td>
				</tr>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<td>Employe:</td>
					<td>${banqueForm.compte.employe.nomEmploye}</td>
				</tr>
			</table>
		</div>
		
		<div>
			<f:form action="saveOperation" modelAttribute="banqueForm">
				<f:hidden path="code"/>
				<table>
					<tr>
						<td><div>Versement: <f:radiobutton path="typeOperation" value="VER" onclick="this.form.submit()"/></div></td>
						<td><div>Retrait: <f:radiobutton path="typeOperation" value="RET" onclick="this.form.submit()"/></div></td>
						<td><div>Virement: <f:radiobutton path="typeOperation" value="VIR" onclick="this.form.submit()"/></div></td>
					</tr>
				</table>
				<table>
					<c:if test="${not empty banqueForm.typeOperation}">
						<tr>
							<td>Montant: <f:input path="montant"/> </td>
							<td> <f:errors path="montant" /> </td>
						</tr>
						<c:if test="${banqueForm.typeOperation.equals('VIR')}">
							<td>Vers le compte: <f:input path="codeDest"/> </td>
							<td> <f:errors path="codeDest" /> </td>
						</c:if>
						<tr>
							<td> <input type="submit" name="action" value="submit"> </td>
						</tr>
					</c:if>
				</table>			
			</f:form>
		</div>
		
		<div>
			<c:if test="${not empty banqueForm.operations}">
				<table class="table">
					<tr>
						<th>NUMERO OPERATION</th>
						<th>DATE</th>
						<th>TYPE</th>
						<th>MONTANT</th>
						<th>EMPLOYE</th>
					</tr>
					<c:forEach items="${banqueForm.operations}" var="var">
						<tr>
							<td>${var.numeroOperation}</td>
							<td>${var.dateOperation}</td>
							<td>${var}</td>	
							<td>${var.montant}</td>
							<td>${var.employe.nomEmploye}</td>
						</tr>
					</c:forEach>
				</table>
		</c:if>
		<c:if test="${empty banqueForm.operations}">
			No operations.
		</c:if>
	</div>		
	
	</c:if>

	<c:if test="${not empty banqueForm.exception}">
		<div>${banqueForm.exception}</div>
	</c:if>

</body>

</html>