package toulousemusee

class DemandeVisite {

    String code
    Date dateDebutPeriode
    Date dateFinPeriode
    int nbPersonnes
    String statut
    Date dateDemande

    static hasMany = [musees:Musee]

    static belongsTo = [Musee]

    static constraints = {
    }
}
