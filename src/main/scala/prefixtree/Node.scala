package prefixtree

import scala.annotation.tailrec
import scala.collection.mutable

private case class Node[T](var value: Option[T], mapping: mutable.Map[Char, Node[T]]) {
  final def size: Int = (if (value.isDefined) 1 else 0) + mapping.values.map(_.size).sum

  final def longestMatchingPrefix(word: String): Option[T] = longestMatchingPrefix(word.toList, None)

  @tailrec
  private def longestMatchingPrefix(chars: List[Char], longestMatch: Option[T]): Option[T] = {
    chars match {
      case Nil => longestMatch
      case c :: tail => mapping.get(c) match {
        case Some(node) => node.longestMatchingPrefix(tail, node.value.orElse(this.value))
        case None => longestMatch
      }
    }
  }

  final def insert(word: String, value: T): Unit = insert(word, word.toList, value)

  @tailrec
  private def insert(word: String, chars: Seq[Char], value: T): Unit = {
    chars match {
      case Nil if this.value.isEmpty => this.value = Some(value)
      case Nil => ()
      case c :: tail =>
        mapping.get(c) match {
          case Some(node) => node.insert(word, tail, value)
          case None =>
            val newNode = Node.empty[T]
            mapping.addOne(c -> newNode)
            newNode.insert(word, tail, value)
        }
    }
  }

  final def get(word: String): Option[T] = get(word.toList)

  @tailrec
  private def get(chars: Seq[Char]): Option[T] = chars match {
    case Nil => this.value
    case c :: tail => mapping.get(c) match {
      case Some(node) => node.get(tail)
      case None => None
    }
  }
}

private object Node {
  final def empty[T]: Node[T] = Node[T](None, mutable.Map.empty[Char, Node[T]])
}
