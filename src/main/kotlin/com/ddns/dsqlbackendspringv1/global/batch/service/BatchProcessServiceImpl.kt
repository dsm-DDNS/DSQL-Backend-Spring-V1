package com.ddns.dsqlbackendspringv1.global.batch.service

import com.ddns.dsqlbackendspringv1.global.batch.data.entity.BatchPost
import com.ddns.dsqlbackendspringv1.global.batch.data.entity.BatchWritePost
import com.ddns.dsqlbackendspringv1.global.komoran.service.KomoranService
import com.ddns.dsqlbackendspringv1.infra.alarm.AlarmService
import com.ddns.dsqlbackendspringv1.infra.ncloud.clova.ClovaService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class BatchProcessServiceImpl(
    private val komoranService: KomoranService,
    private val clovaService: ClovaService,
    private val alarmService: AlarmService
): BatchProcessService {


    @Async
    override fun process(post: BatchPost): BatchWritePost {
        var writePost = BatchWritePost(
            post
        )

        //Komoran 통해 주요 단어 추출
        val tags = komoranService.extractContent(post.title)
        writePost.addTagList(tags)
        post.content?.let {
            //Clova를 통해 Content 요약
            val short = clovaService.extractContent(post.title, post.content!!)
            writePost.insertShortContent(short)
        }
//        //SNS Upload
//        //tistory
//        val out = tistorySnsService.upload(writePost.title, writePost.shrtCnt!!, writePost.tags!!)
//        println(out)
//        //Alarm
//        alarmService.sendAlarm(writePost.title)

        return writePost
    }

}