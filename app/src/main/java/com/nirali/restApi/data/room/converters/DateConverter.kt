/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :5:38 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi.data.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nirali.restApi.domain.model.Source

class SourceConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromSource(source: Source?): String {
        return source?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        return sourceString?.let {
            val type = object : TypeToken<Source?>() {}.type
            gson.fromJson(it, type)
        }
    }
}
