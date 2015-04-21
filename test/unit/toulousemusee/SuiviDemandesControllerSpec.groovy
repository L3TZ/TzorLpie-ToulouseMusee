package toulousemusee

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SuiviDemandesController)
@Mock([DemandeVisite])
class SuiviDemandesControllerSpec extends Specification {

    def demande

    def setup() {
        demande = new DemandeVisite(dateDebutPeriode: new Date(2015,8,16),
                dateFinPeriode:new Date(2015,8,17),
                nbPersonnes: 3,
                dateDemande: new Date(),
                statut: 'En cours de traitement',
                code: UUID.randomUUID().toString()).save()
    }

    void "Test the suiviDemande action with a valid code returns the correct model"() {

        given:"Un code de demande valide"
        controller.params.codeDemande = demande.code

        when:"The suiviDemande action is executed"
        controller.suiviDemande()

        then:
        model.infoDemande==demande
    }

    void "Test the suiviDemande action with an invalid code returns the correct model"() {

        given:"Un code de demande valide"
        controller.params.codeDemande = 5

        when:"The suiviDemande action is executed"
        controller.suiviDemande()

        then:
        model.retourFormulaire == 'Le code n\'est pas valide.'
    }

}