/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import java.util.Optional;
import org.immutables.value.Value;

import akka.Done;

/**
 * This interface defines all the commands that the HelloWorld entity supports.
 * 
 * By convention, the commands should be inner classes of the interface, which makes it simple to get a
 * complete picture of what commands an entity supports.
 */
public interface HelloCommand extends Jsonable {

  /**
   * A command to switch the greeting message.
   *
   * It has a reply type of {@link akka.Done}, which is sent back to the caller when all the events
   * emitted by this command are successfully persisted.
   */
  @Value.Immutable
  @ImmutableStyle
  @JsonDeserialize(as = UseGreetingMessage.class)
  public interface AbstractUseGreetingMessage extends HelloCommand, CompressedJsonable,
      PersistentEntity.ReplyType<Done> {
    @Value.Parameter
    String getMessage();
  }

  /**
   * A command to say hello to someone using the current greeting message.
   *
   * The reply type is String, and will contain the message to say to that person.
   */
  @Value.Immutable
  @ImmutableStyle
  @JsonDeserialize(as = Hello.class)
  public interface AbstractHello extends HelloCommand, PersistentEntity.ReplyType<String> {
    @Value.Parameter
    String getName();

    Optional<String> getOrganization();
  }

}
