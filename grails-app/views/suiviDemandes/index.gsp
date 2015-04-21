<%--
  Created by IntelliJ IDEA.
  User: stav
  Date: 21/04/15
  Time: 20:13
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
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

    #suiviForm {
        width: 400px;
        padding:20px;
        margin-left: auto;
        margin-right: auto;
        text-align: right;
    }

    #suiviForm input {
        margin-top:5px;
    }


    .erreurBloc {
        width:80%;
        margin-right: auto;
        margin-left: auto;
        margin-top: 20px;
        border:2px solid red;
        padding:20px;
        color:red;
    }
    #recapDemande {
        width:60%;
        border : 1px solid grey;
        padding:20px;
        margin-left: auto;
        margin-right: auto;
        margin-top: 40px;
    }
    #recapDemande span {
        margin-top:10px;
    }
    </style>
</head>

<body>

<g:if test="${retourFormulaire}">
    <div class="erreurBloc">
        ${retourFormulaire}
    </div>
</g:if>

<g:form id="suiviForm" url="[action: 'suiviDemande']" >
    <label>Code de la demande :</label>
    <g:field type="text" name="codeDemande" required="true"/>
    <g:actionSubmit value="Suivre demande" action="suiviDemande"/>
</g:form>

<g:if test="${infoDemande}">
    <div id="recapDemande">
        <span>Code de la demande :</span>
        <span><g:fieldValue bean="${infoDemande}" field="code" /></span><br/>
        <span>Date de la demande :</span>
        <span><g:fieldValue bean="${infoDemande}" field="dateDemande" /></span><br/>
        <span>Nb de personne(s) :</span>
        <span><g:fieldValue bean="${infoDemande}" field="nbPersonnes" /></span><br/>
        <span>Statut de la demande :</span>
        <span><g:fieldValue bean="${infoDemande}" field="statut" /></span><br/>
        <g:if test="${infoDemande.statut=="Refusée"}">
            <span>Aucun guide disponible sur cette période.</span>
        </g:if>
        <g:if test="${infoDemande.statut=="Acceptée"}">
            <g:fieldValue bean="${infoDemande}" field="dateDebutPeriode" /><br/>
            <g:fieldValue bean="${infoDemande}" field="dateFinPeriode" /><br/>
        </g:if>
    </div>
</g:if>

</body>
</html>