package com.ddns.dsqlbackendspringv1.global.batch.config

import com.ddns.dsqlbackendspringv1.global.batch.data.entity.BatchPost
import com.ddns.dsqlbackendspringv1.global.batch.data.entity.BatchWritePost
import com.ddns.dsqlbackendspringv1.global.batch.data.mapper.IsTrueRowMapper
import com.ddns.dsqlbackendspringv1.global.batch.data.mapper.PostRowMapper
import com.ddns.dsqlbackendspringv1.global.batch.service.BatchProcessService
import com.ddns.dsqlbackendspringv1.global.database.DatabaseConfiguration
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.launch.support.SimpleJobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.JdbcCursorItemReader
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate


@Configuration
class JobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val dbSource: DatabaseConfiguration,
    @Qualifier("writeJdbcTemplate")
    private val writeJdbcTemplate: JdbcTemplate,
    private val batchProcessService: BatchProcessService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        const val CHUNK_SIZE: Int = 10
        const val TITLE_COLUMN = "title"
        const val URL_COLUMN = "url"
        const val CREATE_AT_COLUMN = "create_at"
        const val CONTENT_COLUMN = "content"
        const val SHORT_CNT_COLUMN = "short_content"
        const val TAGS_COLUMN = "tags"
        const val IMG_COLUMN = "img"
        const val READ_TABLE_NAME = "POST"
        const val WRITE_TABLE_NAME = "POST"
        const val JOB_NAME = "Uploading Job"
        const val LIMIT_SIZE = 30
    }



    @Bean
    fun dsqlDataProcessingJob(): Job {
        return jobBuilderFactory.get(JOB_NAME)
            .incrementer(RunIdIncrementer())
            .start(dsqlPostProcessingStep())
            .build()
    }


    @Bean
    fun dsqlPostProcessingStep(): Step{
        return stepBuilderFactory.get("dsqlPostProcessingStep")
            .chunk<BatchPost, BatchWritePost>(CHUNK_SIZE)
            .reader(dsqlReader())
            .processor(dsqlProcessor())
            .writer(dsqlItemWriter())
            .build()
    }

    @Bean
    fun dsqlReader(): JdbcCursorItemReader<BatchPost> {
        return JdbcCursorItemReaderBuilder<BatchPost>()
            .fetchSize(CHUNK_SIZE)
            .dataSource(dbSource.readDatasource())
            .rowMapper(PostRowMapper())
            .sql("SELECT $TITLE_COLUMN, $URL_COLUMN, $CREATE_AT_COLUMN, $CONTENT_COLUMN, $IMG_COLUMN FROM $READ_TABLE_NAME ORDER BY $CREATE_AT_COLUMN Desc")
            .fetchSize(LIMIT_SIZE)
            .name("jdbcCursorItemReader")
            .build()
    }


    @Bean
    fun dsqlProcessor(): ItemProcessor<BatchPost, BatchWritePost> {
        return ItemProcessor {
            if (!writeJdbcTemplate.query(
                    "SELECT EXISTS(" +
                            "SELECT * FROM " +
                            WRITE_TABLE_NAME +
                            " WHERE title = " + "'" + it.title + "'" + " LIMIT 1) as isTrue;", IsTrueRowMapper()
                )[0].isTrue) {
                log.info(it.title)
                return@ItemProcessor batchProcessService.process(it)
            }
            return@ItemProcessor null
        }
    }



    @Bean
    fun dsqlItemWriter(): ItemWriter<BatchWritePost> {
        return ItemWriter {

        }
    }


}
