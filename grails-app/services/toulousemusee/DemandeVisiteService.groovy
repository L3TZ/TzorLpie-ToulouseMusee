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
                code: UUID.randomUUID().toString())
        listeMusees.each {demandeInstance.addToMusees(it)}
        def demandeInstanceSave = demandeInstance.save(flush: true)
        demandeInstanceSave ?: demandeInstance
    }
}
