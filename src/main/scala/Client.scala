/**
  * Created by yjtsai on 2016/2/23.
  */
import akka.actor._

case class Send(msg: String) extends Msg
case class NewMsg(servername : String, from: String, msg: String) extends Msg
case class Info(servername : String, msg: String) extends Msg

class Client(val username: String, server: ActorRef) extends Actor {

  server ! Connect(username)

  def receive = {
    case NewMsg(servername, from, msg) => {
      println(f"[Room : $servername][$username%s's client] - $from%s: $msg%s")
    }
    case Send(msg) => server ! Broadcast(msg)
    case Info(servername, msg) => {
      println(f"[Room : $servername][$username%s's client] - $msg%s")
    }
    case Disconnect => {
      self ! PoisonPill
    }
  }
}