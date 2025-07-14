package model

data class Bet(val amount: Double) {
    companion object {
        fun parseToBet(input: String): Bet {
            val amount = input.trim().toDoubleOrNull() ?: throw IllegalArgumentException("Bet must be a valid number")
            if (amount < 0) throw IllegalArgumentException("Amount should be greater than 0")
            return Bet(amount)
        }
    }
}
