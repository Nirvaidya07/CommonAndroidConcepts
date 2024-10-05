/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :3:01 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.sample.recipe.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nirali.sample.recipe.domain.model.Attributes

class DogConverters {

    val gson = Gson()

    @TypeConverter
    fun fromAttributes(attributes: Attributes): String? {
        return gson.toJson(attributes)
    }

    @TypeConverter
    fun toAttributes(json: String?): Attributes? {
        return json?.let {
            val type = object : TypeToken<Attributes?>() {}.type
            gson.fromJson(it, type)
        }
    }


}