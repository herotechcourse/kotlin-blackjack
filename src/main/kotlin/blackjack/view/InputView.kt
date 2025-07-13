package blackjack.view

object InputView {
    fun readPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = readln().trim()
        require(input.isNotBlank()) { "Please enter a name." }
        val trimmedInput = input.split(",").map { it.trim() }
        require(trimmedInput.isNotEmpty()) { "Please enter a name." }
        require(trimmedInput.toSet().size == trimmedInput.size) { "There are duplicates in the names." }
        trimmedInput.forEach { require(it.isNotEmpty()) { "Please enter a valid name." } }
        return trimmedInput
    }

    fun readYesOrNo(name: String): Boolean {
        println("\nWould $name like to draw another card? (y for yes, n for no)")
        while (true) {
            val input = readln().trim()
            when (input) {
                "y" -> return true
                "n" -> return false
                else ->
                    println("Please write 'y' for 'yes' or 'n' for 'no'.")
            }
        }
    }

    fun <T> retryable(inputMethod: () -> T): T {
        while (true) {
            try {
                return inputMethod()
            } catch (err: IllegalArgumentException) {
                println("${err.message}")
            }
        }
    }
}
