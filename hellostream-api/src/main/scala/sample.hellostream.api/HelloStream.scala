/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.hellostream.api

import akka.NotUsed
import akka.stream.javadsl.Source
import com.lightbend.lagom.javadsl.api.Descriptor
import com.lightbend.lagom.javadsl.api.ScalaService._
import com.lightbend.lagom.javadsl.api.Service
import com.lightbend.lagom.javadsl.api.ServiceCall

/**
  * The hello stream interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the HelloStream service.
  */
trait HelloStream extends Service {
  def stream(): ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  def descriptor: Descriptor = {
    named("hellostream").withCalls(namedCall("hellostream", stream _))
  }
}
