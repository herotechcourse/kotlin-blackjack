package blackjack.model.table

import blackjack.model.Bet
import blackjack.model.states.Init
import blackjack.model.states.State
import blackjack.view.ErrorMessage

class Player(
    override val name: String,
    override var state: State = Init(),
    var bet: Bet = Bet.Companion.UNPLACED,
) : Participant() {
    init {
        require(name.isNotBlank()) { ErrorMessage.NAME_BLANK.toString() }
        require(name.length <= 15) { ErrorMessage.NAME_LENGTH.toString() }
        require(name.all { it.isLetter() }) { ErrorMessage.NAME_LETTERS.toString() }
    }

    override fun toString(): String {
        return name
    }
}
