package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {

    @Unroll
    void "test la validite d'une adresse valide"(String unNumero, String uneRue, String unCodePostal, String uneVille) {

        given: "un adresse initialisée avec un numéro ou non, et une rue, un code postal et une ville non vides"
        Adresse adresse= new Adresse(numero: unNumero, rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est valide"
        adresse.validate() == true

        where:
        unNumero       |   uneRue          |   unCodePostal         |   uneVille
        "un numéro"    |   "une rue"       |   "un code postal"     |   "une ville"
        ""             |   "une rue"       |   "un code postal"     |   "une ville"
        null           |   "une rue"       |   "un code postal"     |   "une ville"
    }

    @Unroll
    void "test l'invalidité d'une adresse invalide"(String uneRue, String unCodePostal, String uneVille) {

        given: "un adresse initialisée avec une rue, un code postal ou une ville vides ou nuls"
        Adresse adresse= new Adresse(rue: uneRue, codePostal: unCodePostal, ville: uneVille)

        expect: "l'adresse est invalide"
        adresse.validate() == false

        where:
        uneRue          |   unCodePostal         |   uneVille
        ""              |   "un code postal"     |   "une ville"
        "une rue"       |   ""                   |   "une ville"
        "une rue"       |   "un code postal"     |   ""
        null            |   "un code postal"     |   "une ville"
        "une rue"       |   null                 |   "une ville"
        "une rue"       |   "un code postal"     |   null
    }
}
