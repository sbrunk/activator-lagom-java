/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import com.lightbend.lagom.serialization.Jsonable;
import org.immutables.value.Value;
import org.immutables.value.Value.Immutable;

/**
 * This interface defines all the events that the HelloWorld entity supports.
 * 
 * By convention, the events should be inner classes of the interface, which makes it simple to get a
 * complete picture of what events an entity has.
 */
public interface HelloEvent extends Jsonable {

  /**
   * An event that represents a change in greeting message.
   */
  @Immutable
  @ImmutableStyle
  @JsonDeserialize(as = GreetingMessageChanged.class)
  interface AbstractGreetingMessageChanged extends HelloEvent {
    @Value.Parameter
    String getMessage();
  }

}
