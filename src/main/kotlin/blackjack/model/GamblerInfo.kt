package blackjack.model

@JvmInline
value class GamblerInfo(val name: String) {
    init {
        require(name.isNotBlank()) { ERROR_MESSAGE }
    }

    companion object {
        private const val ERROR_MESSAGE = "Name cannot be blank"
    }
}
