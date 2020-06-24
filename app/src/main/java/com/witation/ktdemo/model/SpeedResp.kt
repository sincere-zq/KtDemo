package com.witation.ktdemo.model

data class SpeedResp<T>(val status: Int, val msg: String, val result: T)

data class NewsResult(
    var channel: String,
    val num: Int,
    val list: List<NewsList>
)

data class NewsList(
    var title: String,
    val time: String,
    val src: String,
    val category: String,
    val pic: String,
    val content: String,
    val url: String,
    val weburl: String
)

data class Astro(val astroid: Int, val astroname: String, val date: String, val pic: String)

data class AstroFortunneResult(
    val astroid: Int,
    val astroname: String,
    val year: YearFortunne,
    val week: WeekFortunne,
    val today: TodayFortunne,
    val tomorrow: TomorrowFortunne,
    val month: MonthFortunne
)

data class YearFortunne(
    val date: String,
    val summary: String,
    val money: String,
    val career: String,
    val love: String
)

data class WeekFortunne(
    val date: String,
    val money: String,
    val career: String,
    val love: String,
    val health: String,
    val job: String
)

data class TodayFortunne(
    val date: String,
    val presummary: String,
    val star: String,
    val color: String,
    val number: String,
    val summary: String,
    val money: String,
    val career: String,
    val love: String,
    val health: String
)

data class TomorrowFortunne(
    val date: String,
    val presummary: String,
    val star: String,
    val color: String,
    val number: String,
    val summary: String,
    val money: String,
    val career: String,
    val love: String,
    val health: String
)

data class MonthFortunne(
    val date: String,
    val summary: String,
    val money: String,
    val career: String,
    val love: String
)