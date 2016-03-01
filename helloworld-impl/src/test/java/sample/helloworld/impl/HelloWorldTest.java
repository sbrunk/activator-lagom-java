package sample.helloworld.impl;

import static org.junit.Assert.assertEquals;

import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver.Outcome;
import java.util.Collections;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.Done;
import akka.actor.ActorSystem;
import akka.testkit.JavaTestKit;


public class HelloWorldTest {

  static ActorSystem system;

  @BeforeClass
  public static void setup() {
    system = ActorSystem.create("HelloWorldTest");
  }

  @AfterClass
  public static void teardown() {
    JavaTestKit.shutdownActorSystem(system);
    system = null;
  }

  @Test
  public void testHelloWorld() {
    PersistentEntityTestDriver<HelloCommand, HelloEvent, WorldState> driver =
        new PersistentEntityTestDriver<>(system, new HelloWorld(), "world-1");

    Outcome<HelloEvent, WorldState> outcome1 = driver.run(Hello.of("Alice"));
    assertEquals("Hello, Alice!", outcome1.getReplies().get(0));
    assertEquals(Collections.emptyList(), outcome1.issues());

    Outcome<HelloEvent, WorldState> outcome2 = driver.run(
        UseGreetingMessage.of("Hi"),
        Hello.of("Bob"));
    assertEquals(1, outcome2.events().size());
    assertEquals(GreetingMessageChanged.of("Hi"), outcome2.events().get(0));
    assertEquals("Hi", outcome2.state().getMessage());
    assertEquals(Done.getInstance(), outcome2.getReplies().get(0));
    assertEquals("Hi, Bob!", outcome2.getReplies().get(1));
    assertEquals(2, outcome2.getReplies().size());
    assertEquals(Collections.emptyList(), outcome2.issues());
  }

}
