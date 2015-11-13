class UrlMappings {

	static mappings = {
        "/$controller/$id"(parseRequest:true){
            action = [GET:"show", DELETE: "delete", PUT: "update"]
        }
        "/$controller/"(parseRequest:true){
            action = [GET:"index", POST: "save",DELETE: "delete"]
        }
        "/$controller/search"(parseRequest:true){
            action = [POST:"search"]
        }
        "/$controller/totalCount"(parseRequest:true){
            action = [GET:"totalCount"]
        }
        "/$controller/$action?"(parseRequest:true){
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
