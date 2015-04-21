package toulousemusee

class SuiviDemandesController {

    def index() {}

    def suiviDemande() {
        def demande = DemandeVisite.findByCode(params.codeDemande)

        if (demande==null)
        {
            render(view:'index', model: [retourFormulaire:'Le code n\'est pas valide.'])
        }
        else
        {
            render(view: 'index', model: [infoDemande: demande])
        }
    }
}
