package blackjack

data class Card(val suit: Suit, val rank: Rank) {
    fun display(): String {
        return "${rank.symbol}${suit.symbol}"
    }

    fun getScore(): Int {
        return rank.value
    }

    fun isAce(): Boolean {
        return rank == Rank.ACE
    }
}

enum class Suit(val symbol: String) {
    HEART("♥"),
    DIAMOND("♦"),
    CLUB("♣"),
    SPADE("♠")
}

enum class Rank(val symbol: String, val value: Int) {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11)
}
