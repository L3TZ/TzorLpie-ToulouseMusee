package toulousemusee

class DemandeVisiteController {

    SessionService sessionService

    def index() {
        def listeMuseesPrefs = sessionService.getListeMuseesPrefs(session)
        [listeMuseesPrefs: listeMuseesPrefs]
    }

    def creationDemande() {

    }
}
