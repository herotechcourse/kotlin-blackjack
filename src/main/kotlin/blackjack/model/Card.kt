package blackjack.model

data class Card(val name: String) {
    val digit = name.substring(0, name.length - 1)
}
