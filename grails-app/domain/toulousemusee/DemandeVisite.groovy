package toulousemusee

class DemandeVisite {

    String code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonnes
    String statut
    Date dateDemande

    static hasMany = [musees:Musee]

    static belongsTo = Musee

    static constraints = {
        nbPersonnes max: 6, min: 1
        dateDebutPeriode validator: {val ->
            val >= new Date()
        }
        dateFinPeriode validator: {val, obj ->
            val >= obj.dateDebutPeriode
        }
    }
}
