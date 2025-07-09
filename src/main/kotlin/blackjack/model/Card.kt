package blackjack.model

data class Card(val suit: Suit, val index: Int) {
    init {
        require(index in 1..13)
    }
}
