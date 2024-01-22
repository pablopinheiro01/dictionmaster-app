package br.com.dictionmaster.exceptions

class AllAttemptsMadeBuyAppException(message: String): Exception(message) {
    constructor(): this("Buy App")
}