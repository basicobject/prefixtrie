package prefixmatcher

import scala.annotation.tailrec
import scala.collection.mutable

case class Node(var value: Option[String], mapping: mutable.Map[Char, Node]) {
  @tailrec
  final def longestMatchingPrefix(chars: List[Char], longestMatch: Option[String]): String = {
    chars match {
      case Nil => longestMatch.getOrElse("")
      case c :: tail => mapping.get(c) match {
        case Some(node) => node.longestMatchingPrefix(tail, node.value.orElse(this.value))
        case None => longestMatch.getOrElse("")
      }
    }
  }

  def insert(word: String): Unit = insert(word, word.toList)

  @tailrec
  private def insert(word: String, chars: Seq[Char]): Unit = {
    chars match {
      case Nil => this.value = Some(word)
      case c :: tail =>
        mapping.get(c) match {
          case Some(node) => node.insert(word, tail)
          case None =>
            val newNode = Node.empty
            mapping.addOne(c -> newNode)
            newNode.insert(word, tail)
        }
    }
  }

  def search(word: String): Boolean = {
    search(word.toList)
  }

  @tailrec
  private def search(chars: Seq[Char]): Boolean = chars match {
    case Nil => this.value.isDefined
    case c :: tail => mapping.get(c) match {
      case Some(node) => node.search(tail)
      case None => false
    }
  }
}

object Node {
  final def empty: Node = Node(None, mutable.Map.empty[Char, Node])
}
