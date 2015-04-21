package toulousemusee


import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    MuseeService museeService
    JeuTestMuseeService jeuTestMuseeService

    void "test du moteur de recherche sur les musées"() {

        given:"les musées fournis par le jeu de test "
        jeuTestMuseeService

        when:"on cherche les musées dont le nom contient 'ARCHIVES' "
        List<Musee> res = museeService.searchMusees("ARCHIVES",null,null,5,0)

        then:"on récupère uniquement le musée 1"
        res.size() == 1
        res*.id.contains(jeuTestMuseeService.musee1.id)

        when:"on cherche les musées dont le code postal est '31500' "
        res = museeService.searchMusees(null,"31500",null,5,0)

        then:"on récupère uniquement le musée 1"
        res.size() == 1
        res*.id.contains(jeuTestMuseeService.musee1.id)

        when:"on cherche les musées dont le nom de la rue contient 'ARCHIVES' "
        res = museeService.searchMusees(null,null,"ARCHIVES",5,0)

        then:"on récupère uniquement le musée 1"
        res.size() == 1
        res*.id.contains(jeuTestMuseeService.musee1.id)

        when:"on cherche les musées dont le nom contient 'pasUnNom' "
        res = museeService.searchMusees("pasUnNom",null,null,5,0)

        then: "on ne récupère aucune inscription"
        res.size() == 0

        when:"on ne donne pas de critères de recherche"
        res = museeService.searchMusees(null,null,null,5,0)

        then: "on récupère les 5 premiers musées"
        res.size() == 5

    }





}
