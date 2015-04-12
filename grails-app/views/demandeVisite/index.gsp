<%--
  Created by IntelliJ IDEA.
  User: stav
  Date: 12/04/15
  Time: 18:41
--%>

<%@ page import="toulousemusee.DemandeVisite" contentType="text/html;charset=UTF-8" %>
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

        #recapMuseesPrefs {
            width:60%;
            margin-left: auto;
            margin-right: auto;
            margin-top: 30px;
        }
        #demandeForm {
            width: 400px;
            padding:20px;
            margin-left: auto;
            margin-right: auto;
            text-align: right;
        }

        #demandeForm input,#demandeForm select {
            margin-top:5px;
        }

        #demandeForm input[type="number"] {
            width:35px;
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
    </style>
</head>

<body>

<g:hasErrors bean="${retourCreation}">
    <div class="erreurBloc">
        <g:renderErrors bean="${retourCreation}" as="list"  />
    </div>
</g:hasErrors>

<g:form id="demandeForm" url="[action: 'creationDemande']" >
    <label>Date de début :</label>
    <g:datePicker name="dateDebutPeriode" value="${new Date()}" precision="day" relativeYears="[0..1]"></g:datePicker><br/>
    <label>Date de fin :</label>
    <g:datePicker name="dateFinPeriode" value="${ new Date((new Date()).getTime() + (1000 * 60 * 60 * 24))}" precision="day" relativeYears="[0..1]"></g:datePicker><br/>
    <label>Nombre de personne(s) (6 max.):</label>
    <g:field type="number" name="nbPersonnes" min="1" max="6" required="true" value="1"/>
    <g:actionSubmit value="Demander visite" action="creationDemande"/>
</g:form>

    <table id="recapMuseesPrefs">
        <thead>
            <tr>
                <th>Musée(s) demandé(s) :</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${listeMuseesPrefs}" status="i" var="museePref">
               <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                   <td>${museePref.key}</td>
               </tr>
            </g:each>
        </tbody>
    </table>
</body>
</html>