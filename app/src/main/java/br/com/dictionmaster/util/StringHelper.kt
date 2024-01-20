package br.com.dictionmaster.util

fun String.firstCharToUpperCase(): String {
    return this.replaceFirstChar { it.uppercase() }.toLowerCase()
}