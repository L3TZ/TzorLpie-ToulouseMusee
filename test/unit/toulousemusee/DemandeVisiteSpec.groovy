package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    @Unroll
    void "test la validite d'une demande de visite valide"(String unCode, Date uneDateDebutPeriode, Date uneDateFinPeriode, int unNbPersonne, String unStatut, Date uneDateDemande) {

        given: "une demande de visite initialisée avec un code non nul, une date de début période et une date de fin période non nulless, un nombre de personnes entre 1 et 6, un statut non vide, une date de demande non nulle"
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDebutPeriode, dateFinPeriode: uneDateFinPeriode, nbPersonnes: unNbPersonne, statut: unStatut,dateDemande: uneDateDemande)

        expect: "la demande de visite est valide"
        demandeVisite.validate() == true

        where:
        unCode      |       uneDateDebutPeriode              |   uneDateFinPeriode                |   unNbPersonne        |   unStatut                    |   uneDateDemande
        "un code"   |       new Date(2015,6,17)              |   new Date(2015,6,18)              |   3                   |   "En cours de traitement"    |   new Date()
    }

    @Unroll
    void "test l'invalidité d'une demande de visite invalide"(String unCode, Date uneDateDebutPeriode, Date uneDateFinPeriode, int unNbPersonne, String unStatut, Date uneDateDemande) {

        given: "une demande de visite initialisée avec un code nul, une date de début période et une date de fin période nulles, un nombre de personnes plus petit que 1 ou plus grand que 6, un statut vide, une date de demande nulle"
        DemandeVisite demandeVisite = new DemandeVisite(code: unCode, dateDebutPeriode: uneDateDebutPeriode, dateFinPeriode: uneDateFinPeriode, nbPersonnes: unNbPersonne, statut: unStatut,dateDemande: uneDateDemande)

        expect: "la demande de visite est valide"
        demandeVisite.validate() == false

        where:
        unCode      |       uneDateDebutPeriode              |   uneDateFinPeriode                |   unNbPersonne        |   unStatut                    |   uneDateDemande
        ""          |       new Date(2015,6,17)              |   new Date(2015,6,18)              |   3                   |   "En cours de traitement"    |   new Date()
        "un code"   |       null                             |   new Date(2015,6,18)              |   3                   |   "En cours de traitement"    |   new Date()
        "un code"   |       new Date(2015,6,17)              |   null                             |   3                   |   "En cours de traitement"    |   new Date()
        "un code"   |       new Date(2015,6,18)              |   new Date(2015,6,17)              |   3                   |   "En cours de traitement"    |   new Date()
        "un code"   |       new Date(2015,6,17)              |   new Date(2015,6,18)              |   0                   |   "En cours de traitement"    |   new Date()
        "un code"   |       new Date(2015,6,17)              |   new Date(2015,6,18)              |   7                   |   "En cours de traitement"    |   new Date()
        "un code"   |       new Date(2015,6,17)              |   new Date(2015,6,18)              |   3                   |   ""                          |   new Date()
        "un code"   |       new Date(2015,6,17)              |   new Date(2015,6,18)              |   3                   |   "traitement"                |   new Date()
        "un code"   |       new Date(2015,6,17)              |   new Date(2015,6,18)              |   3                   |   "En cours de traitement"    |   null
    }


}
