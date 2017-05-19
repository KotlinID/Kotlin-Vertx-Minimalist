package org.jasoet.kotlin

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import org.jasoet.kotlin.controller.MainController
import org.jasoet.kotlin.extension.logger
import org.jasoet.kotlin.extension.propertiesConfig
import org.jasoet.kotlin.extension.retrieveConfig
import org.jasoet.kotlin.extension.useLogback
import org.jasoet.kotlin.verticle.MainVerticle


/**
 * [Documentation Here]
 *
 * @author Deny Prasetyo
 */

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            useLogback()
            val log = logger(Application::class)

            log.info("Initialize Vertx Components")
            val vertx = Vertx.vertx()
            val properties = propertiesConfig("application-config.properties")
            val config = vertx.retrieveConfig(properties).toBlocking().first()
            val router = Router.router(vertx)
            log.info("Deploying Main Verticle")

            val mainController = MainController(router)
            val mainVerticle = MainVerticle(mainController)

            vertx.deployVerticle(mainVerticle, DeploymentOptions().apply {
                this.config = config
            })
        }
    }
}
