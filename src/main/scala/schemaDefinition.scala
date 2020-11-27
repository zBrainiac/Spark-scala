import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

object schemaDefinition {
    val schemaTestCSV: StructType = StructType(Array(
      StructField("key", DataTypes.IntegerType, nullable = true),
      StructField("sensorid", DataTypes.IntegerType, nullable = true),
      StructField("location", DataTypes.IntegerType, nullable = true),
      StructField("lat", DataTypes.DoubleType, nullable = true),
      StructField("lon", DataTypes.DoubleType, nullable = true),
      StructField("timestamp", DataTypes.TimestampType, nullable = true),
      StructField("pressure", DataTypes.DoubleType, nullable = true),
      StructField("temperature", DataTypes.DoubleType, nullable = true),
      StructField("humidity", DataTypes.DoubleType, nullable = true)))


    val schemaLoopupCSV: StructType = StructType(Array(
      StructField("sensorid", DataTypes.IntegerType, nullable = false),
      StructField("city", DataTypes.StringType, nullable = false),
      StructField("lat", DataTypes.DoubleType, nullable = false),
      StructField("lon", DataTypes.DoubleType, nullable = false)))
}
