package com.kos.exv.models

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class AgeTest{

    private fun dateValue(day:Int,month:Int,year:Int):Calendar = run {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.timeZone = Age.timeZone
        calendar.set(year,month,day)
        calendar
    }



    @Test
    fun age(){

        assertEquals(12 ,
            Age.age(
                dateValue(12,1,2002),
                dateValue(12,1,1990)
            )
        )
        assertEquals(12 ,
            Age.age(
                dateValue(15,1,2002),
                dateValue(12,1,1990)
            )
        )

        assertEquals(11 ,
            Age.age(
                dateValue(15,1,2002),
                dateValue(16,1,1990)
            )
        )

        assertEquals(1 ,
            Age.age(
                dateValue(5,3,1999),
                dateValue(5,3,1998)
            )
        )
        assertEquals(0 ,
            Age.age(
                dateValue(5,3,1999),
                dateValue(6,3,1998)
            )
        )

        assertEquals(1 ,
            Age.age(
                dateValue(1,1,1999),
                dateValue(31,0,1998)
            )
        )
    }
}