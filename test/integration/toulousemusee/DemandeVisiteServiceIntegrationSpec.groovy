package toulousemusee



import spock.lang.*

/**
 *
 */
class DemandeVisiteServiceIntegrationSpec extends Specification {

    DemandeVisiteService demandeVisiteService

    void "test création d'une demande de visite à partir d'une liste de musées préférés"() {

        given:"des musées"
        def gest = new Gestionnaire(nom: 'GEST TEST').save()
        def adr = new Adresse(numero: '2',rue: 'ADR TEST',codePostal: '31500',ville: 'TOULOUSE' ).save()
        def musee = new Musee(adresse: adr,
                gestionnaire: gest,
                nom: 'MUSEE TEST',
                horairesOuverture: 'Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30 pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet.',
                accesBus: '36, 38',
                accesMetro: 'Roseraie (A)',
                telephone: '05 61 61 63 33').save()
        def musee2 = new Musee(adresse: adr,
                gestionnaire: gest,
                nom: 'MUSEE TEST 2',
                horairesOuverture: 'Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30 pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet.',
                accesBus: '36, 38',
                accesMetro: 'Roseraie (A)',
                telephone: '05 61 61 63 33').save()

        and:"une liste de musées"
        def listeMusees = new ArrayList<Musee>()
        listeMusees.add(musee)
        listeMusees.add(musee2)

        when: "on tente de créer une demande de visite pour cette liste de musées"
        DemandeVisite resultDemandeVisite = demandeVisiteService.createDemande(listeMusees,new Date(2015,6,17),new Date(2015,6,18),3,)

        then:"la demande de visite résultante n'a pas d'erreur"
        !resultDemandeVisite.hasErrors()

        and:"la demande de visite résultante a un id"
        resultDemandeVisite.id

        and:"la demande de visite résultante est bien présente en base"
        DemandeVisite.findById(resultDemandeVisite.id) != null

        and:"les musées ont dans leur liste de demande la nouvelle demande de visite"
        musee.demandevisites.contains(resultDemandeVisite)
        musee2.demandevisites.contains(resultDemandeVisite)
    }
}
