package blackjack.view

object InputView {
    fun readPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = readln().trim()
        val trimmedInput = input.split(",")
        require(trimmedInput.isNotEmpty()) { "Please enter a name." }
        trimmedInput.forEach { require(it.isNotEmpty()) { "Please enter a valid name." } }
        return trimmedInput
    }

    fun readYesOrNo(name: String): Boolean {
        println("\nWould $name like to draw another card? (y for yes, n for no)")
        val input = readln().trim()
        return when (input) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("Please write 'y' for 'yes' or 'n' for 'no'.")
        }
    }
}
