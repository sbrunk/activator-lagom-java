/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.hellostream.impl

import java.util.concurrent.CompletableFuture.completedFuture
import javax.inject.Inject

import akka.NotUsed
import akka.stream.javadsl.Source
import com.lightbend.lagom.javadsl.api.ServiceCall
import sample.hellostream.api.HelloStream
import sample.helloworld.api.HelloService

/**
  * Implementation of the HelloString.
  */
class HelloStreamImpl @Inject()(val helloService: HelloService) extends HelloStream {

  import converter.ServiceCallConverter._

  override def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]] = request =>
    completedFuture(
      request
        .asScala
        .mapAsync(8)(name => helloService.hello(name).invoke())
        .asJava
    )
}
