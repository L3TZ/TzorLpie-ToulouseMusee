package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {

    @Unroll
    void "test la validite d'un gestionnaire valide"(String unNom, def _) {

        given: "un gestionnaire initialisé avec un nom"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est valide"
        gestionnaire.validate() == true

        and: "il n'est gestionnaire d'aucun musée"
        !gestionnaire.musees

        where:
        unNom       | _
        "un nom"    | _

    }

    @Unroll
    void "test l'invalidité d'un gestionnaire non valide"(String unNom, def _) {

        given: "un gestionnaire initialisé avec un nom vide"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire n'est pas valide"
        gestionnaire.validate() == false


        where:
        unNom       | _
        ""          | _

    }
}
