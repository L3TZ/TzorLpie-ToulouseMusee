package toulousemusee

class Adresse {

    String numero
    String rue
    String codePostal
    String ville


    static constraints = {
        rue blank:false
        numero nullable: true
        codePostal blank:false
        ville blank:false
    }
}
