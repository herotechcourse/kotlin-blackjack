package blackjack.model

enum class Suit(val symbol: String) {
    DIAMONDS("♦"),
    HEARTS("♥"),
    CLUBS("♣"),
    SPADES("♠"),
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
    ACE("A", 1),
}

abstract class Card(val suit: Suit, val rank: Rank) {
    abstract val score: Int
    val isAce: Boolean get() = rank == Rank.ACE

    override fun toString(): String = "${rank.symbol}${suit.symbol}"
}

class NumberCard(suit: Suit, rank: Rank) : Card(suit, rank) {
    override val score: Int = rank.value

    init {
        require(rank.value in 2..10) { "Invalid rank for NumberCard: ${rank.symbol}" }
    }
}

class FaceCard(suit: Suit, rank: Rank) : Card(suit, rank) {
    override val score: Int = 10

    init {
        require(rank in listOf(Rank.JACK, Rank.QUEEN, Rank.KING)) {
            "Invalid rank for FaceCard: ${rank.symbol}"
        }
    }
}

class AceCard(suit: Suit) : Card(suit, Rank.ACE) {
    override val score: Int = 1
}
