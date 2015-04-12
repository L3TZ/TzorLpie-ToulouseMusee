package toulousemusee

class HomeController {

    MuseeService museeService

    def index() {}

    def recherche() {
        def offsetTmp = params.int('offset') ?: 0
        def museeList = museeService.searchMusees(params.nomMusee, params.codePostal, params.nomRue, 5, offsetTmp)
        params.max=5;
        params.offset = offsetTmp+2;
        render(view : 'index',model:[museeInstanceList: museeList, museeInstanceCount: museeList.getTotalCount(),params: params])
    }
}
