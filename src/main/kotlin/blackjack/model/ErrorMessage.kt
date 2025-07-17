package blackjack.model

import blackjack.model.Player.Companion.MAX_NAME_LENGTH
import blackjack.model.Rules.BET_AMOUNT_UNIT
import blackjack.model.Rules.MIN_BET_AMOUNT

enum class ErrorMessage(val message: String) {
    MAX_TRIES("Maximum tries of ${Rules.MAX_TRIES} reached."),
    PLAYER_RANGE("Only ${Players.MIN} - ${Players.MAX} players accepted."),
    EMPTY_INPUT("Input is empty."),
    INVALID_INPUT("Input is invalid."),
    NAME_BLANK("Name cannot be blank."),
    NAME_LENGTH("Name cannot be longer than $MAX_NAME_LENGTH characters."),
    MIN_BET("Bet must be at least $MIN_BET_AMOUNT."),
    UNIT_BET("Bet must be multiple of $BET_AMOUNT_UNIT."),
    NOT_NUMBER("Bet must be number."),
    NAME_LETTERS("Names must contain only letters."),
    ;

    override fun toString() = "[ERROR] $message"
}
