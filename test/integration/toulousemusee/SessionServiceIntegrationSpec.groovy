package toulousemusee

import org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpSession
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import spock.lang.*

/**
 *
 */
class SessionServiceIntegrationSpec extends Specification {

    /*
    SessionService sessionService
    JeuTestMuseeService jeuTestMuseeService


    void "test de la récupération de la liste de musées préférés"(){

        when: "on récupère la liste des musées préférés"
        List <Musee> res = sessionService.getListeMuseesPrefs(session)

        then: "la liste de musées n'est pas nulle"
        res != null
    }

    void "test de l'ajout et de la suppression dans la liste de musées préférés"(){

        given: "les musées fournis par le jeu de test"
        jeuTestMuseeService

        when: "on ajoute un musée à la liste"
        sessionService.ajoutMuseePref(session, jeuTestMuseeService.musee1.id)

        then: "la liste contient un musée"
        List <Musee> res = sessionService.getListeMuseesPrefs(session)
        res.size() == 1

        when: "on supprime un musée de la liste"
        sessionService.suppMuseePref(session, jeuTestMuseeService.musee1.id)

        then: "le musée n'est plus dans la liste"
        List <Musee> res2 = sessionService.getListeMuseesPrefs(session)
        res2.size() == 0
    }
    */
}
