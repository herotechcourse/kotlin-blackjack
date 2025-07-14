package blackjack.model

import blackjack.model.Player.Companion.MAX_NAME_LENGTH

fun validatePlayerName(name: String) {
    require(name.isNotBlank()) { ErrorMessage.NAME_BLANK.toString() }
    require(name.length <= MAX_NAME_LENGTH) { ErrorMessage.NAME_LENGTH.toString() }
    require(name.all { it.isLetter() }) { ErrorMessage.NAME_LETTERS.toString() }
}

class Player(
    override val name: String,
    override var handCards: HandCards = HandCards(),
    override val bet: Int
) : Participant() {
    init {
        validatePlayerName(name)
        require(bet >= 1000) { ErrorMessage.MIN_BET.toString() }
    }

    override fun toString(): String {
        return name
    }

    companion object {
        const val MAX_NAME_LENGTH = 15
    }
}
