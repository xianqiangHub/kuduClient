package com.aaaa

import org.apache.kudu.spark.kudu._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row

object SelectClient {

  def main(args: Array[String]) {

    val Array(kuduMasters, tableName, viewName, sql) = args

    val spark = SparkSession.builder.appName("KuduSparkExample").getOrCreate()
    val kuduContext = new KuduContext(kuduMasters, spark.sqlContext.sparkContext)
    import spark.implicits._
    try {
      // Read the updated table using SparkSQL.
      val sqlDF = spark.sqlContext.read.options(
        Map("kudu.master" -> kuduMasters, "kudu.table" -> tableName)).kudu

      sqlDF.createOrReplaceTempView(viewName)

      spark.sqlContext.sql(sql).show

//      val readCols = Seq(nameCol, idCol)
//      val readRDD = kuduContext.kuduRDD(spark.sparkContext, tableName, readCols)
//      readRDD.foreach(println(_))

    } catch {
      case unknown: Throwable => ""
        throw unknown
    } finally {
      spark.close()
    }
  }
}
