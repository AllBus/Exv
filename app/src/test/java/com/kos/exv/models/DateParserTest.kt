package com.kos.exv.models

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class DateParserTest {

    data class Rule(val text:String,val hasResult:Boolean, val resultDate:Long)

    private fun dateValue(day:Int,month:Int,year:Int):Long = run {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.timeZone = Age.timeZone
        calendar.set(year,month,day)
        calendar.timeInMillis
    }

    @Test
    fun extractDate() {

        listOf(
            Rule("", false, 0),
            Rule("abra", false, 0),
            Rule("ab-ra", false, 0),
            Rule("---", false, 0),
            Rule("--", false, 0),
            Rule("-", false, 0),
            Rule("34-5", false, 0),
            Rule("23--78", false, 0),
            Rule("234-67-455-5665", false, 0),
            Rule("56-12a-7688", false, 0),
            Rule("1-1,1", false, 0),

            Rule("10-0-10", true, dateValue(10, 11, 9)),
            Rule("23-07-2000", true, dateValue(23, 6, 2000)),
            Rule("09-05-6712", true, dateValue(9, 4, 6712)),
            Rule("31-12-1999", true, dateValue(31, 11, 1999)),
            Rule("6-3-905", true, dateValue(6, 2, 905)),
            Rule("423-207-20011", true, dateValue(423, 206, 20011)),
            Rule("00-00-00000", true, dateValue(0, 11, -1)),
            Rule("0-0-0", true, dateValue(0, 11, -1)),

            Rule("2097-01-15", true, dateValue(15, 0, 2097)),
            Rule("2000-07-23", true, dateValue(23, 6, 2000)),
            Rule("6712-05-09", true, dateValue(9, 4, 6712)),
            Rule("1999-12-31", true, dateValue(31, 11, 1999))
        ).forEach {

            val res = Age.parse(it.text)
            assertEquals(it.hasResult, res.defined)
            assertEquals(it.resultDate, res.birthday)
        }


    }
}