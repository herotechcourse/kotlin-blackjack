package blackjack.model

enum class Suit(val symbol: String) {
    HEARTS("♥"),
    CLUBS("♣"),
    SPADES("♠"),
    DIAMONDS("♦"),
    NONE("?"), ;

    companion object {
        fun fromSymbol(symbol: Char): Suit = entries.firstOrNull { it.symbol == symbol.toString() } ?: NONE
    }
}
