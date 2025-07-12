package blackjack.model

data class Card(val suit: Suit, val rank: Rank) {
    enum class Color(val ansiCode: String) {
        RED_ON_WHITE("\u001B[38;5;9;48;5;15m"), // bright red
        BLACK_ON_WHITE("\u001B[38;5;0;48;5;15m"),
        RESET("\u001B[0m"),
    }

    enum class Suit(val symbol: String, val color: Color) {
        DIAMONDS("♦", Color.RED_ON_WHITE),
        HEARTS("♥", Color.RED_ON_WHITE),
        CLUBS("♣", Color.BLACK_ON_WHITE),
        SPADES("♠", Color.BLACK_ON_WHITE),
    }

    // maybe change the name Rank
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
        ACE("A", 11),
    }

    override fun toString(): String {
        return "${suit.color.ansiCode}${rank.symbol}${suit.symbol}${Color.RESET.ansiCode}"
    }
}
