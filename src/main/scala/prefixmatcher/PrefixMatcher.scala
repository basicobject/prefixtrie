package prefixmatcher
import java.io.File
import scala.io.Source

object PrefixMatcher extends App {
  args.headOption match {
    case Some(name) => println(name)
      val file = new File(name)
      val prefixes = Source.fromFile(file).mkString("").split("\n").map(_.trim).toSeq

      val prefixTreeBuilder = new PrefixTreeBuilder(prefixes)
      val prefixTree = prefixTreeBuilder.build()
      if (prefixTree.matches("2y3fKTS"))
        println("Matches the prefix")
      else
        println("Does not matches the prefix")

      val longest = prefixTree.longestMatchingPrefix("2y3fKTSDeepak")

      println("Longest matching prefix is -> " + longest)

    case None => println("Please input the input file")
  }

}
