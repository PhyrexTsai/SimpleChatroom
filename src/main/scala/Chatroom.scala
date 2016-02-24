/**
  * Created by yjtsai on 2016/2/23.
  */
import akka.actor._

object Chatroom {
  /**
    * Create multiple chatroom and each chatromm can accept multiple clients
    * @param args
    */
  def main(args : Array[String]) {
    // ActorSystem initialize
    val system = ActorSystem("System")

    // create server
    val server = system.actorOf(ServerActor.props("Chat 1"))
    val server2 = system.actorOf(ServerActor.props("Chat 2"))

    // create client
    val c1 = system.actorOf(ClientActor.props("Sam", server))

    c1 ! Send("HI")

    val c2 = system.actorOf(ClientActor.props("Helen", server))

    c2 ! Send("Hello")

    val c3 = system.actorOf(ClientActor.props("John", server))

    c3 ! Send("Hello!!!")

    // Disconnect
    c3 ! Disconnect

    val c4 = system.actorOf(ClientActor.props("Paul", server2))

    val c5 = system.actorOf(ClientActor.props("Mary", server2))

    c4 ! Send("New guy")

  }
}