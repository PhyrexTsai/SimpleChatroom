/**
  * Created by yjtsai on 2016/2/23.
  */
import akka.actor._

case class Send(msg: String) extends Msg
case class Message(serverName : String, from: String, msg: String) extends Msg
case class Info(serverName : String, msg: String) extends Msg

/**
  * Create a Client Actor, require username and server Actor
  * @param username
  * @param server
  */
class Client(val username: String, server: ActorRef) extends Actor {

  // when a client is create, connect to server
  server ! Connect(username)

  def receive = {
    // display normal message
    case Message(serverName, from, msg) => {
      println(s"[Room : $serverName][$username's client] - $from: $msg")
    }
    case Send(msg) => server ! Broadcast(msg)
    // display system message, join or leave or etc...
    case Info(serverName, msg) => {
      println(s"[Room : $serverName][$username's client] - $msg")
    }
    case Disconnect => {
      // http://stackoverflow.com/questions/13847963/akka-kill-vs-stop-vs-poison-pill
      self ! PoisonPill
    }
  }
}

object ClientActor {
  def props(username: String, server: ActorRef): Props = Props(new Client(username, server))
}