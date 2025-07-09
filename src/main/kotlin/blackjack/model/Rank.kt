package blackjack.model

enum class Rank(val digit: String, val value: Int) {
    ACE("A", 11),
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
    NONE("0", 0),
    ;

    companion object {
        fun of(digit: String): Rank {
            return when (digit) {
                "A" -> ACE
                "2" -> TWO
                "3" -> THREE
                "4" -> FOUR
                "5" -> FIVE
                "6" -> SIX
                "7" -> SEVEN
                "8" -> EIGHT
                "9" -> NINE
                "10" -> TEN
                "J" -> JACK
                "Q" -> QUEEN
                "K" -> KING
                else -> NONE
            }
        }
    }
}
