import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala._


object WikiFun {
  def main(args: Array[String]) : Unit = {

    val see : StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val edits = see.addSource(new WikipediaEditsSource())
    val userEditsVolume: DataStream[(String, Int)] = edits
      .keyBy(_.getUser) //this gives us a stream of WikipediaEditsSource that has a string key,the user name
      .timeWindow(Time.seconds(5))//tumbling (non overlapping) window
      .fold(("", 0))((acc, event) => (event.getUser, acc._2 + event.getByteDiff))//<-userEditsVolume
    userEditsVolume.print()
    see.execute("Wikipedia User Edit Volume")//necessary to actually start the flink job
  }
}


//1-The 0 on the fold is starting point (0bytes) as the streaming will process the bytes would be added accordingly
//
