package prefixtree

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.wordspec.AnyWordSpec


class PrefixTreeSpec extends AnyWordSpec {
  import prefixmatcher.Util.randomWords

  "initializes a new prefix tree of value type String" in {
    val tree = new DefaultPrefixTree[String]
    tree.size mustBe 0
  }

  "inserts a new item to the tree" in {
    val tree = new DefaultPrefixTree[String]
    val word = "apple"
    tree.insert(word, word)
    tree.size mustBe 1
  }

  "won't inserts duplicates" in {
    val tree = new DefaultPrefixTree[String]
    val word = "apple"
    tree.insert(word, word)
    tree.insert(word, word)
    tree.size mustBe 1
  }

  "gets the inserted item from the tree" in {
    val tree = new DefaultPrefixTree[String]
    val words = randomWords
    words.foreach(w => tree.insert(w, w.toUpperCase))
    val word = words.last
    tree.get(word) mustBe Some(word.toUpperCase)
  }

  "checks if the tree contains the value" in {
    val tree = new DefaultPrefixTree[Int]
    val words = randomWords
    words.foreach(w => tree.insert(w, 1))
    val word = words.last
    assert(words.forall(tree.contains))
    tree.contains("apple") mustBe false
    tree.contains("kangaroo") mustBe false
    tree.contains("app") mustBe false
  }

  "returns number of words inserted" in {
    val tree = new DefaultPrefixTree[Int]
    val words = randomWords
    words.foreach(w => tree.insert(w, 1))
    tree.size mustBe words.size
  }

  "returns longest matching prefix" in {
    val tree = new DefaultPrefixTree[String]
    val words = Set("a", "abc", "abcd", "abcde", "abcdefg", "abcdefgh", "abcdefghijk")
    words.foreach(w => tree.insert(w, w))
    tree.longestMatchingPrefix("abcd-apple") mustBe Some("abcd")
    tree.longestMatchingPrefix("apple") mustBe Some("a")
  }

}
