package prefixmatcher

import java.util.concurrent.TimeUnit
import scala.util.Random

object Util {
    def randomWords: Set[String] = Set.fill(Random.nextInt(100000)) { Random.alphanumeric.take(Random.nextInt(50)).mkString("") }

    def duration(block: => Unit): Unit = {
      val startTime = System.nanoTime()
      block
      println("Duration => " + TimeUnit.MILLISECONDS
        .convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS))
    }
}
