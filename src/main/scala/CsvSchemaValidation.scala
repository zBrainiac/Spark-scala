import org.apache.spark.sql.SparkSession

object CsvSchemaValidation {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("CSVschemaValidation")
      .master("local")
      .getOrCreate()

    val currentDirectory = new java.io.File(".").getCanonicalPath

    val TestCSVDF = sparkSession.read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .schema(schemaDefinition.schemaTestCSV)
      .load("data/testCSV_short.csv").toDF()

    TestCSVDF.printSchema()
    System.out.println("number of records: " + TestCSVDF.count)

    TestCSVDF.na.drop.count
    TestCSVDF.na.drop("any").show()

    TestCSVDF.na.drop("any")
      .write.format("csv")
      .option("header", "True")
      .mode("overwrite")
      .option("sep", ",")
      .save(s"file:///${currentDirectory}/data/df_output_csv")

    TestCSVDF.na.drop("any")
      .write.format("json")
      .option("header", "True")
      .mode("overwrite")
      .option("sep", ",")
      .save(s"file:///${currentDirectory}/data/df_output_json")
  }
}