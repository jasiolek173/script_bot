import akka.actor.{ActorContext, ActorRef, Props}
import io.scalac.slack.BotModules
import io.scalac.slack.bots.system.{CommandsRecognizerBot, HelpBot}

class ExampleBotsBundle() extends BotModules {
  override def registerModules(context: ActorContext, websocketClient: ActorRef): Unit = {
    context.actorOf(Props(classOf[CommandsRecognizerBot], BotRunner.eventBus), "commandProcessor")
    context.actorOf(Props(classOf[HelpBot], BotRunner.eventBus), "helpBot")
    context.actorOf(Props(classOf[WeatherBot], BotRunner.eventBus), "WeatherBot")
  }
}