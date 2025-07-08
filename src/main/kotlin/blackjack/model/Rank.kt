package blackjack.model

enum class Rank(val digit: String, val isBigAce: Boolean, val value: Int) {
    ACE("A", false, 1),
    BIG_ACE("A", true, 11),
    TWO("2", false, 2),
    THREE("3", false, 3),
    FOUR("4", false, 4),
    FIVE("5", false, 5),
    SIX("6", false, 6),
    SEVEN("7", false, 7),
    EIGHT("8", false, 8),
    NINE("9", false, 9),
    TEN("10", false, 10),
    JACK("J", false, 10),
    QUEEN("Q", false, 10),
    KING("K", false, 10),
    NONE("0", false, 0),
    ;

    companion object {
        fun of(
            digit: String,
            isBigAce: Boolean,
        ): Rank {
            return when {
                digit == "A" && isBigAce -> BIG_ACE
                digit == "A" -> ACE
                digit == "2" -> TWO
                digit == "3" -> THREE
                digit == "4" -> FOUR
                digit == "5" -> FIVE
                digit == "6" -> SIX
                digit == "7" -> SEVEN
                digit == "8" -> EIGHT
                digit == "9" -> NINE
                digit == "10" -> TEN
                digit == "J" -> JACK
                digit == "Q" -> QUEEN
                digit == "K" -> KING
                else -> NONE
            }
        }
    }
}
