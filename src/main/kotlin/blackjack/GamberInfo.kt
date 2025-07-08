package blackjack

@JvmInline
value class GamberInfo(val name: String) {
    init {
        require(name.isNotBlank()) {}
        require(name.lowercase() != RESERVED_NAME) {}
    }

    companion object {
        private const val RESERVED_NAME = "dealer"
    }
}
