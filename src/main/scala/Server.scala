/**
  * Created by yjtsai on 2016/2/23.
  */

import akka.actor._

case class Connect(username: String) extends Msg
case class Broadcast(msg: String) extends Msg
case object Disconnect extends Msg

/**
  * Create a named Server Actor
  * @param name
  */
class Server(val name : String) extends Actor {

  // Use a tuple for the List element
  var clients = List[(String, ActorRef)]();

  def receive = {
    case Connect(username) => {
      broadcast(Info(name, s"$username joined the chat"))
      // use double :: means add the (username, sender) to the first element of clients List
      clients = (username, sender) :: clients
      context.watch(sender)
    }
    case Broadcast(msg) => {
      val username = getUsername(sender)
      broadcast(Message(name, username, msg))
    }
    case Terminated(client) => {
      val username = getUsername(client)
      // filter sender is self
      clients = clients.filter(sender != _._2)
      broadcast(Info(name, s"$username left the chat"))
    }
  }

  def broadcast(msg: Msg) {
    // boardcast to all client Actor and send message
    // _1 username
    // _2 ActorRef
    clients.foreach(x => x._2 ! msg)
  }

  def getUsername(actor: ActorRef): String = {
    // find the same ActorRef from clients to get name of Actor
    // because filter will return a List(String, ActorRef), so here use the head to get the only one element in the List, and return the username of it
    clients.filter(actor == _._2).head._1
  }
}

object ServerActor {
  def props(name: String): Props = Props(new Server(name))
}