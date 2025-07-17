package model

@JvmInline
value class Bet(val amount: Double) {
    init {
        require(amount >= 0) { "Amount should be greater than 0" }
    }
}
