package br.com.dictionmaster.exceptions

class DataNotFoundException(message: String): Exception(message) {
    constructor(): this("Word not found")
}