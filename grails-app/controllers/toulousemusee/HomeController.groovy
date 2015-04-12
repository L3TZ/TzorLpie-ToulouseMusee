package toulousemusee

class HomeController {

    MuseeService museeService
    SessionService sessionService

    def index() {
        def listeMuseesPrefs = sessionService.getListeMuseesPrefs(session)
        [listeMuseesPrefs: listeMuseesPrefs]
    }

    def recherche() {
        def listeMuseesPrefs = sessionService.getListeMuseesPrefs(session)

        def offsetTmp = params.int('offset') ?: 0
        def museeList = museeService.searchMusees(params.nomMusee, params.codePostal, params.nomRue, 5, offsetTmp)
        params.max=5;
        render(view : 'index',model:[museeInstanceList: museeList, museeInstanceCount: museeList.getTotalCount(),params: params,listeMuseesPrefs: listeMuseesPrefs])
    }

    def ajoutMuseePref() {
        def listeMuseesPrefs = sessionService.getListeMuseesPrefs(session)

        if (params.id != null) {
            sessionService.ajoutMuseePref(session,params.int('id'))
        }
        render(view : 'index', model:[listeMuseesPrefs: listeMuseesPrefs])
    }
}
