package toulousemusee

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus

    Gestionnaire gestionnaire

    Adresse adresse

    static hasMany = [demandevisites:DemandeVisite]

    static mapping = {
        gestionnaire fetch:'join'
        demandevisites fetch: 'join'
    }

    static constraints = {
        nom blank:false
        horairesOuverture blank:false
        telephone blank:false
        accesBus nullable: true
        accesMetro nullable: true
    }
}
