package blackjack.model

enum class ErrorMessage(val message: String) {
    MAX_TRIES("Maximum tries of ${Rules.MAX_TRIES} reached."),
    PLAYER_RANGE("Only ${Players.MIN} - ${Players.MAX} players accepted."),
    EMPTY_INPUT("Input is empty."),
    INVALID_INPUT("Input is invalid."),
    NAME_BLANK("Name cannot be blank."),
    NAME_LENGTH("Name cannot be longer than 15 characters."),
    MIN_BET("Bet must be at least 1000."),
    NOT_NUMBER("Bet must be number."),
    NAME_LETTERS("Names must contain only letters."),
    ;

    override fun toString() = "[ERROR] $message"
}
