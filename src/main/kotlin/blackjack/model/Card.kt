package blackjack.model

data class Card(val name: String) {
    val digit = name.substring(0, name.length - 1)
    val value = Rank.of(digit).value
}
