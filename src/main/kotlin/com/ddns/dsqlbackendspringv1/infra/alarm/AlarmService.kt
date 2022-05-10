package com.ddns.dsqlbackendspringv1.infra.alarm

interface AlarmService {

    fun sendAlarm(title: String): String
}