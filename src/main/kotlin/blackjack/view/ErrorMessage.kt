package blackjack.view

import blackjack.controller.Controller
import blackjack.model.table.Players

enum class ErrorMessage(val message: String) {
    MAX_TRIES("Maximum tries of ${Controller.Companion.MAX_TRIES} reached."),
    PLAYER_RANGE("Only ${Players.Companion.MIN} - ${Players.Companion.MAX} players accepted."),
    EMPTY_INPUT("Input is empty."),
    BETTING_AMOUNT("Betting amount has to be a positive integer"),
    NAME_BLANK("Name cannot be blank."),
    NAME_LENGTH("Name cannot be longer than 15 characters."),
    NAME_LETTERS("Name must contain only letters."),
    ;

    override fun toString() = "[ERROR] $message"
}
