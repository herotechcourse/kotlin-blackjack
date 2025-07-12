package blackjack.controller

fun String.parseCommaString(): List<String> {
    return split(",").map(String::trim)
}
