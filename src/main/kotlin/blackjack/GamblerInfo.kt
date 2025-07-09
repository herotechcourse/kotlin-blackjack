package blackjack

@JvmInline
value class GamblerInfo(val name: String) {
    init {
        require(name.isNotBlank()) {} //TODO: add err msg
    }

}
