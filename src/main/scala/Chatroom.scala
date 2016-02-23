/**
  * Created by yjtsai on 2016/2/23.
  */
import akka.actor._

object Chatroom {
  def main(args : Array[String]) {
    val system = ActorSystem("System")
    val server = system.actorOf(Props[Server])
    var c1 = system.actorOf(Props(new Client("Sam", server)))

    c1 ! Send("HI")

    val c2 = system.actorOf(Props(new Client("Helen", server)))

    c2 ! Send("Hello")

    val c3 = system.actorOf(Props(new Client("John", server)))

    c3 ! Send("Hello!!!")
  }
}