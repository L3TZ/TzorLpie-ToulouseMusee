package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HomeController)
class HomeControllerSpec extends Specification {

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


}
