package toulousemusee

class DemandeVisiteController {

    SessionService sessionService
    DemandeVisiteService demandeVisiteService

    def index() {
        def listeMuseesPrefs = sessionService.getListeMuseesPrefs(session)
        [listeMuseesPrefs: listeMuseesPrefs]
    }

    def creationDemande() {
        Calendar c1 = GregorianCalendar.getInstance()
        c1.clear()
        c1.set(params.int('dateDebutPeriode_year') ?: 0, (params.int('dateDebutPeriode_month') ?: 0)-1, params.int('dateDebutPeriode_day') ?: 1);
        Calendar c2 = GregorianCalendar.getInstance()
        c2.clear()
        c2.set(params.int('dateFinPeriode_year') ?: 0, (params.int('dateFinPeriode_month') ?: 0)-1, params.int('dateFinPeriode_day') ?: 1);
        //def dateDebutPeriode = new Date(params.int('dateDebutPeriode_year') ?: 0,params.int('dateDebutPeriode_month') ?: 0,params.int('dateDebutPeriode_day') ?: 0 )
        //def dateFinPeriode = new Date(params.int('dateFinPeriode_year') ?: 0,params.int('dateFinPeriode_month') ?: 0,params.int('dateFinPeriode_day') ?: 0 )
        def dateDebutPeriode = c1.getTime()
        def dateFinPeriode = c2.getTime()

        def nbPersonnes = params.int('nbPersonnes') ?: 0
        def listeMuseesPrefs = sessionService.getListeMuseesPrefs(session)
        def listeMuseeInstance = new ArrayList<Musee>()

        def retourCreation

        if (listeMuseesPrefs != null && !listeMuseesPrefs.isEmpty()) {
            for (museeInstance in listeMuseesPrefs) {
                listeMuseeInstance.add(Musee.findById(museeInstance.value))
            }

            retourCreation = demandeVisiteService.createDemande(listeMuseeInstance,dateDebutPeriode,dateFinPeriode,nbPersonnes)
            if (!retourCreation.hasErrors()) {
                [retourCreation: retourCreation.code]
            } else {
                render(view: 'index',model:[retourCreation: retourCreation,listeMuseesPrefs: listeMuseesPrefs])
            }
        }
    }
}
