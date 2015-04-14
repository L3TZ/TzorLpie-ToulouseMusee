package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

    @Unroll
    void "test la validite d'un musée valide"(String unNom, String unHoraireOuverture, String unTelephone, String unAccesMetro, String unAccesBus, Gestionnaire unGestionnaire, Adresse uneAdresse) {

        given: "un musée initialisé avec un nom, un horaire d'ouverture, un téléphone non vides, un accès métro et un accès bus"
        Musee musee = new Musee(nom: unNom, horairesOuverture: unHoraireOuverture, telephone: unTelephone, accesMetro: unAccesMetro, accesBus: unAccesBus, gestionnaire: unGestionnaire, adresse: uneAdresse)

        expect: "le musée est valide"
        musee.validate() == true

        where:
        unNom       |   unHoraireOuverture          |   unTelephone         |   unAccesMetro        |   unAccesBus      |   unGestionnaire      |   uneAdresse
        "un nom"    |   "un horaire d'ouverture"    |   "un téléphone"      |   "un accès métro"    |   "un accès bus"  |   Mock(Gestionnaire)  |   Mock(Adresse)
        "un nom"    |   "un horaire d'ouverture"    |   "un téléphone"      |   ""                  |   ""              |   Mock(Gestionnaire)  |   Mock(Adresse)
        "un nom"    |   "un horaire d'ouverture"    |   "un téléphone"      |   null                |   null            |   Mock(Gestionnaire)  |   Mock(Adresse)

    }

    @Unroll
    void "test l'invalidite d'un musée non valide"(String unNom, String unHoraireOuverture, String unTelephone, Gestionnaire unGestionnaire, Adresse uneAdresse) {

        given: "une activite initialise avec un nom vide ou un horaire d'ouverture vide ou un téléphone vide ou sans gestionnaire ou sans adresse"
        Musee musee = new Musee()

        expect: "l'activite est invalide"
        musee.validate() == false

        where:

        unNom       |   unHoraireOuverture          |   unTelephone     |   unGestionnaire      |   uneAdresse
        ""          |   "un horaire d'ouverture"    |   "un téléphone"  |   Mock(Gestionnaire)  |   Mock(Adresse)
        "un nom"    |   ""                          |   "un téléphone"  |   Mock(Gestionnaire)  |   Mock(Adresse)
        "un nom"    |   "un horaire d'ouverture"    |   ""              |   Mock(Gestionnaire)  |   Mock(Adresse)
        "un nom"    |   "un horaire d'ouverture"    |   "un téléphone"  |   null                |   Mock(Adresse)
        "un nom"    |   "un horaire d'ouverture"    |   "un téléphone"  |   Mock(Gestionnaire)  |   null


    }
}
