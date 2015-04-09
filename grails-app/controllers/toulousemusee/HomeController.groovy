package toulousemusee

class HomeController {

    MuseeService museeService

    def index() {}

    def recherche() {
        def museeList = museeService.searchMusees(params.nomMusee, params.codePostal, params.nomRue)
        render(view:"index",model:[museeInstanceList: museeList, museeInstanceCount: museeList.size()])
    }
}
