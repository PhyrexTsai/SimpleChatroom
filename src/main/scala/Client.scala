/**
  * Created by yjtsai on 2016/2/23.
  */
import akka.actor._

class Client(val username: String, server: ActorRef) extends Actor {

  server ! Connect(username)

  def receive = {
    case NewMsg(from, msg) => {
      println(f"[$username%s's client] - $from%s: $msg%s")
    }
    case Send(msg) => server ! Broadcast(msg)
    case Info(msg) => {
      println(f"[$username%s's client] - $msg%s")
    }
    case Disconnect => {
      self ! PoisonPill
    }
  }
}