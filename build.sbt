name := "wikiFun"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.flink" %% "flink-streaming-scala" % "1.9.0"

libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.0-alpha0"

libraryDependencies += "org.apache.flink" % "flink-metrics-slf4j" % "1.9.0"

libraryDependencies += "org.apache.flink" %% "flink-connector-wikiedits" % "1.9.0"
