package com.atguigu.util

import org.apache.spark.sql.SparkSession

object HiveUtil {

  /**
   * 调整最大分区个数
   */

  def setMaxpartitions(spark:SparkSession) = {
    spark.sql("set hive.exec.dynamic.partition=true")
    spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    spark.sql("set hive.exec.max.dynamic.partitions=100000")
    spark.sql("set hive.exec.max.dynamic.partitions.pernode=100000")
    spark.sql("set hive.exec.max.created.files=100000")
  }

  /**
   * 开启压缩
   */
  def openCompression(sparkSession: SparkSession) = {
    sparkSession.sql("set mapred.output.compress=true")
    sparkSession.sql("set hive.exec.compress.output=true")
  }

  /**
   * 开启动态分区，非严格模式
   */
  def openDynamicPartition(sparkSession: SparkSession) = {
    sparkSession.sql("set hive.exec.dynamic.partition=true")
    sparkSession.sql("set hive.exec.dynamic.partition.mode=nonstrict")
  }

  /**
   * 使用lzo压缩
   */
  def useLzoCompression(spark: SparkSession) = {
    spark.sql("set io.compression.codec.lzo.class=com.hadoop.compression.lzo.LzoCodec")
    spark.sql("set mapred.output.compression.codec=com.hadoop.compression.lzo.LzopCodec")
  }
  /**
   * 使用snappy压缩
   * @param spark
   */
  def useSnappyCompression(spark:SparkSession)={
    spark.sql("set mapreduce.map.output.compress.codec=org.apache.hadoop.io.compress.SnappyCodec");
    spark.sql("set mapreduce.output.fileoutputformat.compress=true")
    spark.sql("set mapreduce.output.fileoutputformat.compress.codec=org.apache.hadoop.io.compress.SnappyCodec")
  }
}
