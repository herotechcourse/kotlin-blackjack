package blackjack

data class Card(val symbol: Symbol, val index: Int) {
    init {
        require(index in 1..13)
    }
}
