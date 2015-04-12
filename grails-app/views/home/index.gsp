<%--
  Created by IntelliJ IDEA.
  User: 21406399
  Date: 08/04/2015
  Time: 14:30
--%>

<%@ page import="toulousemusee.Adresse" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Toulouse Musées</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }
        #museesPrefs {
            width: 200px;
            height: 500px;
            overflow-y: scroll;
            position: fixed;
            top:100px;
            right: 0px;
            background-color: white;
            text-align: center;
            border:1px solid gray;
            padding:20px;
            box-shadow: gray 0 0 2px 2px;
        }

        #museesPrefs ul {
            list-style: none;
        }

        #museesPrefs ul li {
            border-bottom: 1px solid gray;
            padding-bottom: 10px;
        }

        #museesPrefs ul li a {
            margin-top:7px;
        }

        #rechercheForm {
            width: 400px;
            padding:20px;
            margin-left: auto;
            text-align: right;
        }

        #rechercheForm input,#rechercheForm select {
            margin-top:5px;
        }

    .myButton {
        -moz-box-shadow:inset 0px 1px 0px 0px #9acc85;
        -webkit-box-shadow:inset 0px 1px 0px 0px #9acc85;
        box-shadow:inset 0px 1px 0px 0px #9acc85;
        background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #74ad5a), color-stop(1, #68a54b));
        background:-moz-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
        background:-webkit-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
        background:-o-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
        background:-ms-linear-gradient(top, #74ad5a 5%, #68a54b 100%);
        background:linear-gradient(to bottom, #74ad5a 5%, #68a54b 100%);
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#74ad5a', endColorstr='#68a54b',GradientType=0);
        background-color:#74ad5a;
        border:1px solid #3b6e22;
        display:inline-block;
        cursor:pointer;
        color:#ffffff;
        font-family:arial;
        font-size:13px;
        font-weight:bold;
        padding:6px 12px;
        text-decoration:none;
    }
    a.myButton {
        color:white;
    }
    .myButton:hover {
        background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #68a54b), color-stop(1, #74ad5a));
        background:-moz-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
        background:-webkit-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
        background:-o-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
        background:-ms-linear-gradient(top, #68a54b 5%, #74ad5a 100%);
        background:linear-gradient(to bottom, #68a54b 5%, #74ad5a 100%);
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#68a54b', endColorstr='#74ad5a',GradientType=0);
        background-color:#68a54b;
    }
    .myButton:active {
        position:relative;
        top:1px;
    }

    </style>
</head>

<body>

<g:if test="${listeMuseesPrefs.size() > 0}">

    <div id="museesPrefs">
        <h4>Mes musées préférés</h4><br/>
        <ul>
            <g:each in="${listeMuseesPrefs}" status="i" var="museePref">
                <li>${museePref.key} <br/><a class="myButton" href="${createLink(controller: "home",action: "suppMuseePref",params: [id:museePref.value])}">Supprimer</a></li>
            </g:each>
        </ul>
    </div>

</g:if>

<g:form id="rechercheForm" url="[action: 'recherche']">
    <label>Nom du musée: </label>
    <g:textField name="nomMusee"/><br/>




    <label>Nom de la rue: </label>
    <g:textField name="nomRue"/><br/>

    <label>Code postal: </label>
    <g:select name="codePostal"
              from="${Adresse.list().codePostal.unique()}" />



    <g:actionSubmit value="Rechercher" action="recherche"/>
</g:form>

<table>
    <thead>
    <tr>

        <th><g:message code="musee.nom.label" default="Nom" /></th>

        <th><g:message code="musee.telephone.label" default="Téléphone" /></th>

        <th><g:message code="musee.adresse.label" default="Adresse" /></th>

        <th><g:message code="musee.accesMetro.label" default="Métro" /></th>

        <th><g:message code="musee.accesBus.label" default="Bus" /></th>

        <th><g:message code="musee.horairesOuverture.label" default="Horaires" /></th>

        <th><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></th>

        <th></th>

    </tr>
    </thead>
    <tbody>
    <g:each in="${museeInstanceList}" status="i" var="museeInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td>${fieldValue(bean: museeInstance, field: "nom")}</td>

            <td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

            <td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

            <td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>

            <td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>

            <td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

            <td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>

            <g:if test="${!session.listeMusee.containsValue(new Integer(museeInstance.id.intValue()))}">
                <td><a  class="myButton" href="${createLink(controller: "home",action: "ajoutMuseePref",params: [id:fieldValue(bean: museeInstance, field: "id")])}"> Ajouter à ma liste</a></td>
            </g:if>
        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination">
    <g:paginate total="${museeInstanceCount ?: 0}"  controller="home" action="recherche"  params="${params}"/>
</div>



</body>
</html>