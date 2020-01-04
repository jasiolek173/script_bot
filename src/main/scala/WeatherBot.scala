import io.scalac.slack.MessageEventBus
import io.scalac.slack.bots.AbstractBot
import io.scalac.slack.common.{Command, OutboundMessage}
import scalaj.http._

class WeatherBot(override val bus: MessageEventBus) extends AbstractBot {

  override def help(channel: String): OutboundMessage = OutboundMessage(channel,
    s"$name will tell you about weather in city \\n" +
      "Usage: $weather city_name")

  override def act: Receive = {
    case Command("weather", city :: _, message) if city.nonEmpty =>
      val response: HttpResponse[String] = Http("https://api.openweathermap.org/data/2.5/weather").param("q", city).param("appid", "408052efd219b5de03048f92b194d83e").asString
      if (response.code == 200) {
        val data = ujson.read(response.body)
        val temp = data("main")("temp").toString().toDouble - 273.0
        val rounded = BigDecimal(temp).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
        val weather = (data("name") + ", " + data("weather")(0)("description") + ", temp " + rounded+" C").replaceAll("\"", "")
        println(weather)
        publish(OutboundMessage(message.channel, weather))
      }

    case Command("weather", _, message) =>
      publish(OutboundMessage(message.channel, s"No arguments specified!"))
  }
}
