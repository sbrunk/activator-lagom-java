/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.hellostream.impl

import com.google.inject.AbstractModule
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport
import sample.helloworld.api.HelloService
import sample.hellostream.api.HelloStream

/**
  * The module that binds the HelloStream so that it can be served.
  */
class HelloStreamModule extends AbstractModule with ServiceGuiceSupport {
  protected def configure {
    // Bind the HelloStream service
    bindServices(serviceBinding(classOf[HelloStream], classOf[HelloStreamImpl]))
    // Bind the HelloService client
    bindClient(classOf[HelloService])
  }
}
