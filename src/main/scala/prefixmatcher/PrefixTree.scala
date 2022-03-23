package prefixmatcher

import scala.collection.mutable


class PrefixTree {
  val root: Node = Node.empty

  def matches(input: String): Boolean = root.search(input)
  def insert(word: String): Unit = root.insert(word)

  def longestMatchingPrefix(word: String): String = root.longestMatchingPrefix(word.toList, None)
}
