package com.ddns.dsqlbackendspringv1.global.async

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor


@EnableAsync
@Configuration
class AsyncConfiguration : AsyncConfigurer {


    override fun getAsyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.setThreadNamePrefix("async-thread-running-")
        executor.corePoolSize = 5
        executor.maxPoolSize = 50
        executor.setQueueCapacity(500)
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.AbortPolicy())
        executor.initialize()
        return executor
    }
}