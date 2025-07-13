package model

enum class Rank(val value: String) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ;

    companion object {
        fun score(rank: Rank): Int {
            return when (rank) {
                JACK -> 10
                QUEEN -> 10
                KING -> 10
                ACE -> 11
                else -> rank.value.toInt()
            }
        }
    }
}
