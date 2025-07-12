package util

fun String.isYes(): Boolean {
    return this.trim().lowercase() in listOf("y", "yes")
}
