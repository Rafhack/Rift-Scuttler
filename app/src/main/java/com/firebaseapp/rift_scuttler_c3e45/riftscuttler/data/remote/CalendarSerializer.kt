package com.firebaseapp.rift_scuttler_c3e45.riftscuttler.data.remote

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

/**
 * Created by Marcel on 21/09/2016
 */
class CalendarSerializer : JsonSerializer<Calendar>, JsonDeserializer<Calendar> {

    override fun serialize(src: Calendar, typeOfSrc: Type, context: JsonSerializationContext): JsonElement = JsonPrimitive(src.timeInMillis)

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Calendar {
        val c = Calendar.getInstance()
        c.timeInMillis = json.asLong
        return c
    }
}