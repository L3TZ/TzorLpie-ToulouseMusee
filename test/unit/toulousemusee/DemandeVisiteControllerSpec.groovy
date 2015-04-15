package toulousemusee

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

import javax.websocket.Session

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisiteController)
@Mock([SessionService,Gestionnaire,Adresse,Musee, DemandeVisiteService])
class DemandeVisiteControllerSpec extends Specification {

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

    def populateValidParams(params) {
        assert params != null
        params["code"]="un code"
        params["statut"]="En cours de traitement"
        params["nbPersonnes"]= 3
        params["dateDébutPériode"]= new Date(2015,6,17)
        params["dateFinPériode"]= new Date(2015,6,18)
    }

    void "Test the index action returns the correct model"() {

        given:
        def serviceMock = mockFor(SessionService)
        serviceMock.demand.getListeMuseesPrefs {return new TreeMap<String,Integer>()}
        controller.sessionService = serviceMock.createMock()

        when: "The index action is executed"
        def model = controller.index()

        then: "The model is correct"
        model.listeMuseesPrefs.size()==0


    }

    void "Test the creationDemande action returns the correct model"() {

        given:
        def demandeVisiteMock = mockFor(DemandeVisiteService)
        demandeVisiteMock.demand.createDemande { def listeMusees, Date dateDebutPeriode, Date dateFinPeriode, int nbPersonnes -> def demandeInstance = new DemandeVisite(dateDebutPeriode: dateDebutPeriode,
                dateFinPeriode:dateFinPeriode,
                nbPersonnes: nbPersonnes,
                dateDemande: new Date(),
                statut: 'En cours de traitement',
                code: UUID.randomUUID().toString())
            return demandeInstance}

        def serviceMock = mockFor(SessionService)
        serviceMock.demand.getListeMuseesPrefs { def listeMuseesPrefs = new TreeMap<String, Integer>()
                listeMuseesPrefs.put(musee.nom, new Integer((int)musee.id))
                return listeMuseesPrefs}

        controller.sessionService = serviceMock.createMock()
        controller.demandeVisiteService=demandeVisiteMock.createMock()

        when: "The create action is executed"
        def model = controller.creationDemande()

        then: "The model is correctly created"
        model.retourCreation != null

    }
}
