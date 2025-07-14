package blackjack.model

fun validatePlayerName(name: String) {
    require(name.isNotBlank()) { ErrorMessage.NAME_BLANK.toString() }
    require(name.length <= 15) { ErrorMessage.NAME_LENGTH.toString() }
    require(name.all { it.isLetter() }) { ErrorMessage.NAME_LETTERS.toString() }
}

class Player(
    override val name: String,
    override var handCards: HandCards = HandCards(),
) : Participant() {
    init {
        validatePlayerName(name)
    }

    override fun toString(): String {
        return name
    }
}
