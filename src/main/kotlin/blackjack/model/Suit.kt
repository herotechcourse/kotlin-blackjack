package blackjack.model

enum class Suit(val form: String) {
    CLUBS("♣"),
    DIAMONDS("♦"),
    SPADES("♠"),
    HEARTS("♥"),
    ;

    companion object {
        fun of(name: String): Suit {
            return Suit.entries.firstOrNull { it.name == name } ?: throw IllegalArgumentException("Unknown suit: $name")
        }

        fun with(form: String): Suit {
            return Suit.entries.firstOrNull { it.form == form } ?: throw IllegalArgumentException("Unknown suit: $form")
        }
    }
}
