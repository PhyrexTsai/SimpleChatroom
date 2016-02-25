/**
  * Created by yjtsai on 2016/2/23.
  */
import akka.actor._
import akka.util.Timeout


object Chatroom {
  /**
    * Create multiple chatroom and each chatromm can accept multiple clients
    *
    * @param args
    */
  def main(args : Array[String]) {
    var servers = List[(String, ActorRef)]();

    // ActorSystem initialize
    val system = ActorSystem("System")

    // create server
    val server = system.actorOf(ServerActor.props("Chat 1"))
    servers = ("Chat 1", server) :: servers
    val server2 = system.actorOf(ServerActor.props("Chat 2"))
    servers = ("Chat 2", server2) :: servers
    // create client
    val c1 = system.actorOf(ClientActor.props("Sam", server))

    Thread.sleep(1000)

    c1 ! Send("HI")

    Thread.sleep(1000)

    val c2 = system.actorOf(ClientActor.props("Helen", server))

    Thread.sleep(1000)

    c2 ! Send("Hello")

    Thread.sleep(1000)

    val c3 = system.actorOf(ClientActor.props("John", server))

    Thread.sleep(1000)

    c3 ! Send("Hello!!!")

    Thread.sleep(1000)

    // Disconnect
    c3 ! Disconnect

    Thread.sleep(1000)

    val c4 = system.actorOf(ClientActor.props("Paul", server2))

    Thread.sleep(1000)

    val c5 = system.actorOf(ClientActor.props("Mary", server2))

    Thread.sleep(1000)

    c4 ! Send("New guy")

    Thread.sleep(1000)

    servers.reverse.foreach(x => println(x._1))


  }
}