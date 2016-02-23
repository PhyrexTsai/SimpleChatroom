/**
  * Created by yjtsai on 2016/2/23.
  */
import akka.actor._

object Chatroom {
  def main(args : Array[String]) {
    val system = ActorSystem("System")
    val server = system.actorOf(Props(new Server("Chat 1")))
    val server2 = system.actorOf(Props(new Server("Chat 2")))
    var c1 = system.actorOf(Props(new Client("Sam", server)))

    c1 ! Send("HI")

    val c2 = system.actorOf(Props(new Client("Helen", server)))

    c2 ! Send("Hello")

    val c3 = system.actorOf(Props(new Client("John", server)))

    c3 ! Send("Hello!!!")



    c3 ! Disconnect

    val c4 = system.actorOf(Props(new Client("Paul", server2)))
    val c5 = system.actorOf(Props(new Client("Mary", server2)))
    c4 ! Send("New guy")

  }
}