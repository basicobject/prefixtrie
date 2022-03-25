package prefixtree

trait PrefixTree[T] {
  def get(word: String): Option[T]
  def insert(word: String, value: T): Unit
  def contains(word: String): Boolean
  def size: Int
  def longestMatchingPrefix(word: String): Option[T]
}

class DefaultPrefixTree[T] extends PrefixTree[T] {
  private val root: Node[T] = Node.empty[T]

  override def get(word: String): Option[T] = root.get(word)
  override def insert(word: String, value: T): Unit = root.insert(word, value)
  override def contains(word: String): Boolean = root.get(word).isDefined
  override def longestMatchingPrefix(word: String): Option[T] = root.longestMatchingPrefix(word)
  override def size: Int = root.size
}
