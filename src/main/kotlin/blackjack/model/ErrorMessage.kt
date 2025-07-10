package blackjack.model

import blackjack.controller.Controller

enum class ErrorMessage(val message: String) {
    MAX_TRIES("Maximum tries of ${Controller.MAX_TRIES} reached."),
    PLAYER_RANGE("Only ${Players.MIN} - ${Players.MAX} players accepted."),
    EMPTY_INPUT("Input is empty."),
    NAME_BLANK("Name cannot be blank."),
    NAME_LENGTH("Name cannot be longer than 15 characters."),
    NAME_LETTERS("Name must contain only letters.")
    ;

    override fun toString() = "[ERROR] $message"
}
