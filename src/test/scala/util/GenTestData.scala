package util

import prefixmatcher.Util

import java.io.{BufferedOutputStream, FileOutputStream}

object GenTestData extends App {
  val writer = new BufferedOutputStream(new FileOutputStream("prefixes.txt"))
  Util.randomWords.foreach { word => writer.write((word+"\n").getBytes) }
  writer.close()
}
