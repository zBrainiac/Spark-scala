import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}


object test1 {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("sparksql Demo")
      .master("local")
      .getOrCreate()

    val df = sparkSession.read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .schema(CsvSchema)
      .load("data/testCSV_short.csv").toDF()

    df.printSchema()
    System.out.println("number of records: " + df.count)

    df.na.drop.count
    df.na.drop("any").show()

    df.na.drop("any")
      .write.format("csv")
      .option("header", "True")
      .mode("overwrite")
      .option("sep", ",")
      .save("file:////Users/mdaeppen/GoogleDrive/workspace/Spark-scala/data/df_output")
  }

  val CsvSchema: StructType = StructType(Array(
    StructField("key", DataTypes.IntegerType, nullable = true),
    StructField("sensor_id", DataTypes.IntegerType, nullable = true),
    StructField("location", DataTypes.IntegerType, nullable = true),
    StructField("lat", DataTypes.DoubleType, nullable = true),
    StructField("lon", DataTypes.DoubleType, nullable = true),
    StructField("timestamp", DataTypes.TimestampType, nullable = true),
    StructField("pressure", DataTypes.DoubleType, nullable = true),
    StructField("temperature", DataTypes.DoubleType,  nullable = true),
    StructField("humidity", DataTypes.DoubleType, nullable = true)))
}