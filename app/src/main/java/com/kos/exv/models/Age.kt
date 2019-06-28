package com.kos.exv.models

import java.util.*


class Age(val defined:Boolean, val birthday:Long) {

    fun value():Int {
        return value(birthday)
    }

    companion object{

        @JvmStatic
        val timeZone :TimeZone = TimeZone.getTimeZone("UTC")

        @JvmStatic
        fun value(birthdayDate:Long):Int{
            val today = Calendar.getInstance()
            today.timeZone = timeZone
            today.timeInMillis= System.currentTimeMillis()

            val birthday = Calendar.getInstance()
            birthday.timeZone = timeZone
            birthday.timeInMillis = birthdayDate
            return age(today, birthday)

        }

        @JvmStatic
        fun age(today:Calendar, birthday:Calendar):Int  {

            val age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR)
            if (today.get(Calendar.DAY_OF_YEAR) < birthday.get(Calendar.DAY_OF_YEAR)) {
                return age - 1
            }
            return age
        }

        @JvmStatic
        fun parse(dateString: String): Age {
            val dateParts = dateString.split('-')

            if (dateParts.size == 3) {
                try {
                    val calendar = Calendar.getInstance()
                    calendar.clear()
                    calendar.timeZone = timeZone
                    if (dateParts[0].length == 4 && dateParts[1].length == 2 && dateParts[2].length == 2)
                        calendar.set(dateParts[0].toInt(), dateParts[1].toInt()-1, dateParts[2].toInt())
                    else
                        calendar.set(dateParts[2].toInt(), dateParts[1].toInt()-1, dateParts[0].toInt())
                    return Age(true, calendar.timeInMillis)
                } catch (e: Throwable) {

                }
            }
            return Age(false, 0L)
        }
    }
}

