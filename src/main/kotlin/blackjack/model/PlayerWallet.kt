package blackjack.model

class PlayerWallet {
    var balance: Int = 0
        private set
    var bet: Int = 0
        private set

    fun placeBet(amount: Int) {
        require(amount > 0) { "Bet must be a positive number." }
        bet = amount
    }

    fun updateBalance(newEarnings: Int) {
        balance += newEarnings
    }
}
