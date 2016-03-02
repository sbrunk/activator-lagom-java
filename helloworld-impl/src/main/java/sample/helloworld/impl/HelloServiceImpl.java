/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import javax.inject.Inject;
import sample.helloworld.api.GreetingMessage;
import sample.helloworld.api.HelloService;

/**
 * Implementation of the HelloService.
 */
public class HelloServiceImpl implements HelloService {

  private final PersistentEntityRegistry persistentEntityRegistry;

  @Inject
  public HelloServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
    this.persistentEntityRegistry = persistentEntityRegistry;
    persistentEntityRegistry.register(HelloWorld.class);
  }

  @Override
  public ServiceCall<String, NotUsed, String> hello() {
    return (id, request) -> {
      // Look up the hello world entity for the given ID.
      PersistentEntityRef<HelloCommand> ref = persistentEntityRegistry.refFor(HelloWorld.class, id);
      // Ask the entity the Hello command.
      return ref.ask(Hello.of(id));
    };
  }

  @Override
  public ServiceCall<String, GreetingMessage, Done> useGreeting() {
    return (id, request) -> {
      // Look up the hello world entity for the given ID.
      PersistentEntityRef<HelloCommand> ref = persistentEntityRegistry.refFor(HelloWorld.class, id);
      // Tell the entity to use the greeting message specified.
      return ref.ask(UseGreetingMessage.of(request.getMessage()));
    };

  }

}
