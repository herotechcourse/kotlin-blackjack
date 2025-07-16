package model

@JvmInline
value class Bet(val amount: Double) {
    init {
        require(amount >= 0) { "Amount should be greater than 0" }
    }

    companion object {
        fun parseToBet(input: String): Bet {
            val amount = input.trim().toDouble()
            return Bet(amount)
        }
    }
}
