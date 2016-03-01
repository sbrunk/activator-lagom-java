/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import com.lightbend.lagom.serialization.CompressedJsonable;
import java.time.LocalDateTime;
import org.immutables.value.Value;

/**
 * The state for the {@link HelloWorld} entity.
 */
@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = WorldState.class)
public abstract class AbstractWorldState implements CompressedJsonable {

  /**
   * The greeting message.
   */
  @Value.Parameter
  public abstract String getMessage();

  /**
   * When the greeting last changed.
   */
  @Value.Parameter
  public abstract LocalDateTime getTimestamp();
}
