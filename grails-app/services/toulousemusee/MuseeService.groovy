package toulousemusee

import grails.transaction.Transactional
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class MuseeService {

    def searchMusees(String nomPart, String cP, String ruePart,int maxP, int offsetP) {
        def criteria = Musee.createCriteria()
        def res = criteria.list (max: maxP, offset: offsetP){
            setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
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
