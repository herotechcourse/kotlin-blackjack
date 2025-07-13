package blackjack.view

enum class Errors(val message: String) {
    INVALID_INPUT("Invalid Input"),
    INVALID_PLAYERS_NAMES("Invalid Players Names"),
    INVALID_CARD("Invalid Card"),
    INVALID_DRAW("Invalid draw Command"),
}
