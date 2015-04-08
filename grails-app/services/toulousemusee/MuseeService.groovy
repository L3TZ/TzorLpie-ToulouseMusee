package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    List<Musee> searchMusees(String nomPart, String cP, String ruePart) {
        def criteria = Musee.createCriteria()
        List<Musee> res = criteria.list {
            if (nomPart) {
                like 'nom', "%${nomPart}%"
            }
            if (cP) {
                adresse {
                    like 'codePostal', "${cP}"
                }
            }
            if (ruePart) {
                adresse {
                    like 'rue', "%${ruePart}%"
                }
            }
        }
        res
    }
}
