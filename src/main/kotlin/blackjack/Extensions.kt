package blackjack

fun String.isHitOrStand(): Boolean {
    return when (this) {
        "y" -> true
        "n" -> false
        else -> throw IllegalArgumentException("")
    }
}
