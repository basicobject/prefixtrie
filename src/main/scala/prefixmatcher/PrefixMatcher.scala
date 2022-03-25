package prefixmatcher
import java.io.File
import scala.io.Source
import scala.util.{Failure, Success, Using}

object PrefixMatcher extends App {

  args.headOption match {
    case Some(name) => println(name)
      val file = new File(name)
      val  tryStr = Using(Source.fromFile(file))(_.mkString(""))

      tryStr match {
        case Success(str) =>
          val prefixes = str.split("\n").map(_.trim).toSeq
          val prefixTreeBuilder = new PrefixTreeBuilder(prefixes)
          val prefixTree = prefixTreeBuilder.build()

          if (prefixTree.contains("2y3fKTS"))
            println("Matches the prefix")
          else
            println("Does not matches the prefix")

          val longest = prefixTree.longestMatchingPrefix("2y3fKTSApple")

          println("Longest matching prefix is -> " + longest)

        case Failure(exception) =>
          println("Failed to read input file")
          System.exit(-1)
      }

    case None => println("Please input the input file")
  }

}
