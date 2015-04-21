package toulousemusee

import grails.gorm.PagedResultList
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HomeController)
@Mock([Musee, Gestionnaire, Adresse])
class HomeControllerSpec extends Specification {

    Musee musee

    def setup() {
        def gest = new Gestionnaire(nom: 'GEST TEST').save()
        def adr = new Adresse(numero: '2',rue: 'ADR TEST',codePostal: '31500',ville: 'TOULOUSE' ).save()
        musee = new Musee(adresse: adr,
                gestionnaire: gest,
                nom: 'MUSEE TEST',
                horairesOuverture: 'Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30 pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet.',
                accesBus: '36, 38',
                accesMetro: 'Roseraie (A)',
                telephone: '05 61 61 63 33').save()
    }

    void "Test the index action returns the correct model"() {

        given:
        def serviceMock = mockFor(SessionService)
        serviceMock.demand.getListeMuseesPrefs {return new TreeMap<String,Integer>()}
        controller.sessionService = serviceMock.createMock()

        when: "The index action is executed"
        def model = controller.index()

        then: "The model is correct"
        model.listeMuseesPrefs != null
    }

    void "Test the recherche action returns the correct model"() {

        given:
        def serviceMock = mockFor(SessionService)
        serviceMock.demand.getListeMuseesPrefs {return new TreeMap<String, Integer>()}
        controller.sessionService = serviceMock.createMock()

        def museeMock = mockFor(MuseeService)
        museeMock.demand.searchMusees { String nomPart, String cP, String ruePart,int maxP, int offsetP -> def museeList = Mock(PagedResultList)
            return museeList}
        controller.museeService=museeMock.createMock()

        when: "The index action is executed"
        controller.recherche()

        then: "The model is correct"
        model.museeInstanceList != null
        model.museeInstanceCount == 0
        model.listeMuseesPrefs != null
    }

    void "Test the ajoutMuseePref action returns the correct model"() {

        given:
        def serviceMock = mockFor(SessionService)
        serviceMock.demand.getListeMuseesPrefs {return new TreeMap<String, Integer>()}
        controller.sessionService = serviceMock.createMock()

        when: "The index action is executed"
        controller.ajoutMuseePref()

        then: "The model is correct"

    }

    void "Test the suppMuseePref action returns the correct model"() {

        given:
        def serviceMock = mockFor(SessionService)
        serviceMock.demand.getListeMuseesPrefs {return new TreeMap<String, Integer>()}
        controller.sessionService = serviceMock.createMock()

        when: "The index action is executed"
        controller.suppMuseePref()

        then: "The model is correct"

    }


}
