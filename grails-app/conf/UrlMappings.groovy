class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" {
            controller = "HomeController"
            action = "index"
        }
        "500"(view:'/error')
	}
}
