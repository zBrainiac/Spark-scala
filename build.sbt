name := "Spark-scala"

version := "0.1"

scalaVersion := "2.12.12"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.0.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"


// https://mvnrepository.com/artifact/za.co.absa.cobrix/spark-cobol
libraryDependencies += "za.co.absa.cobrix" %% "spark-cobol" % "2.1.1"
