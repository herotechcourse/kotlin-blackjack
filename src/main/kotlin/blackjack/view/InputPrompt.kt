package blackjack.view

object InputPrompt {
    const val GET_NAME_OF_PLAYER = "Enter the names of the players (comma-separated):"

    fun askHitOrStand(name: String): String = "Would $name like to draw another card? (y for yes, n for no)"
    fun askBetAmount(name: String): String = "\nEnter $name’s betting amount:"
}
