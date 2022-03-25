package prefixmatcher

import prefixtree.{PrefixTree, DefaultPrefixTree}

class PrefixTreeBuilder(prefixes: Seq[String]) {
  def build(): PrefixTree[String] = {
    val tree = new DefaultPrefixTree[String]()
    prefixes.foreach(word => tree.insert(word, word))
    tree
  }
}
