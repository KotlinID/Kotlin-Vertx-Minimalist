package org.jasoet.kotlin.controller

import io.vertx.ext.web.Router
import org.jasoet.kotlin.extension.logger

/**
 * [Documentation Here]
 *
 * @author Deny Prasetyo.
 */


class MainController constructor(override val router: Router) : Controller({

    val log = logger(MainController::class)

    get("/").handler { context ->
        val response = context.response()
        response.statusCode = 200
        response.end("Hello World!")
    }

})