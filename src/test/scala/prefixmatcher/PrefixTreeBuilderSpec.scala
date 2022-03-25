package prefixmatcher

import org.scalatest.wordspec.AnyWordSpec
import prefixtree.PrefixTree

import scala.language.postfixOps

class PrefixTreeBuilderSpec extends AnyWordSpec {
  "builds a prefix tree from a list of words" in {
    val words = Seq("apple", "oranges", "mango", "plum")
    val tree = new PrefixTreeBuilder(words).build()
    assert(tree.isInstanceOf[PrefixTree[String]] )
  }
}
