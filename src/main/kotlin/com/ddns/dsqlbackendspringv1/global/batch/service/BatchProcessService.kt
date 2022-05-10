package com.ddns.dsqlbackendspringv1.global.batch.service

import com.ddns.dsqlbackendspringv1.global.batch.data.entity.BatchPost
import com.ddns.dsqlbackendspringv1.global.batch.data.entity.BatchWritePost

interface BatchProcessService {

    fun process(post: BatchPost): BatchWritePost

}