import org.apache.spark.sql.SparkSession

object CsvJoin2DataSets {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("CSVjoin2DataSets")
      .master("local")
      .getOrCreate()


    val currentDirectory = new java.io.File(".").getCanonicalPath

    val TestCsvDF = sparkSession.read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .schema(schemaDefinition.schemaTestCSV)
      .load("data/testCSV_short.csv").toDF()

    val LookupCsvDF = sparkSession.read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .schema(schemaDefinition.schemaLoopupCSV)
      .load("data/lookup.csv").toDF()

    TestCsvDF.printSchema()
    System.out.println("number of records: " + TestCsvDF.count)

    TestCsvDF.na.drop.count
    TestCsvDF.na.drop("any").show()

    LookupCsvDF.printSchema()
    LookupCsvDF.show()


    println("Inner join")
    TestCsvDF.join(LookupCsvDF,TestCsvDF("sensorid") ===  LookupCsvDF("sensorid"),"inner")
      .show(false)

//    val df = spark.sql("select * from t1, t2 where t1.id = t2.id")


    TestCsvDF.na.drop("any")
      .write.format("csv")
      .option("header", "True")
      .mode("overwrite")
      .option("sep", ",")
      .save(s"file:///${currentDirectory}/data/df_output_csv")

    TestCsvDF.na.drop("any")
      .write.format("json")
      .option("header", "True")
      .mode("overwrite")
      .option("sep", ",")
      .save(s"file:///${currentDirectory}/data/df_output_json")
  }
}