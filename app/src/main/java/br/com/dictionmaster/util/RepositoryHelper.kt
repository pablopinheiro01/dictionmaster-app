package br.com.dictionmaster.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

inline fun <reified T> convertToJsonGeneric(obj: List<T>): String? {
    val moshi = Moshi.Builder().build()
    val type: Type = Types.newParameterizedType(List::class.java, T::class.java)
    val adapter: JsonAdapter<List<T>> = moshi.adapter(type)
    return adapter.toJson(obj)
}

inline fun <reified T> convertFromJsonGenericList(value: String): List<T>? {
    val moshi = Moshi.Builder().build()
    val type: Type = Types.newParameterizedType(List::class.java, T::class.java)
    val adapter: JsonAdapter<List<T>> = moshi.adapter(type)
    return adapter.fromJson(value)
}

inline fun <reified T> convertFromJsonGeneric(value: String): T? {
    val moshi = Moshi.Builder().build()
    val type: Type = Types.newParameterizedType(T::class.java)
    val adapter: JsonAdapter<T> = moshi.adapter(type)
    return adapter.fromJson(value)
}