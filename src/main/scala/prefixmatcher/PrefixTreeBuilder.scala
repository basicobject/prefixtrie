package prefixmatcher

class PrefixTreeBuilder(prefixes: Seq[String]) {
  def build(): PrefixTree = {
    val tree = new PrefixTree()
    prefixes.foreach { word =>
      tree.insert(word)
    }

    tree
  }
}
