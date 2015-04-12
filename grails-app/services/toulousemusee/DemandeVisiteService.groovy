package toulousemusee

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {

    def createDemande(def listeMusees,Date dateDebutPeriode, Date dateFinPeriode, int nbPersonnes) {
        def demandeInstance = new DemandeVisite(dateDebutPeriode: dateDebutPeriode,
                dateFinPeriode:dateFinPeriode,
                nbPersonnes: nbPersonnes,
                dateDemande: new Date(),
                statut: 'En cours de traitement',
                musees: listeMusees,
                code: UUID.randomUUID().toString())
        def demandeInstanceSave = demandeInstance.save()
        demandeInstanceSave ?: demandeInstance
    }
}
