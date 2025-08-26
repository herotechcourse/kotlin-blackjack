package blackjack

class InputValidator {
    fun validateNames (input: List<String>) {
        input.forEach { name ->
            require(name.isNotBlank()) { "Input cannot be blank." }
            require(name.length > 2) { "Name should have more than 2 characters." }
        }
    }
}