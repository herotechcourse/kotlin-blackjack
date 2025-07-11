package blackjack.controller

fun String.isHitOrStand(): Boolean {
    return when (this) {
        "y" -> true
        "n" -> false
        else -> throw IllegalArgumentException("The answer must be y or n.")
    }
}

fun String.parseCommaString(): List<String> {
    return split(",").map(String::trim)
}
