package toulousemusee

import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

import javax.servlet.http.HttpSession

@Transactional
class SessionService {


    def getListeMuseesPrefs(GrailsHttpSession session) {
        if (session.listeMusee == null) {
            session.listeMusee = new ArrayList<Integer>()
        }
        session.listeMusee
    }

    def ajoutMuseePref(GrailsHttpSession session, int id) {
        if (Musee.findById(id) != null) {
            def liste = session.listeMusee
            if (!liste.contains(new Integer(id))) {
                liste.add(new Integer(id))
                session.listeMusee = liste
            }
        }
    }

    def suppMuseePref(GrailsHttpSession session, int id) {
        def liste = session.listeMusee
        liste.remove(new Integer(id))
        session.listeMusee = liste
    }

}
