import toulousemusee.JeuTestMuseeService

class BootStrap {

    JeuTestMuseeService jeuTestMuseeService

    def init = { servletContext ->

        jeuTestMuseeService.createJeuTest()
    }
    def destroy = {
    }
}
