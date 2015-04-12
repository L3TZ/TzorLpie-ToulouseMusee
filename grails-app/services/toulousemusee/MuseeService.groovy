package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    def searchMusees(String nomPart, String cP, String ruePart,int maxP, int offsetP) {
        def criteria = Musee.createCriteria()
        def res = criteria.list (max: maxP, offset: offsetP){
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
