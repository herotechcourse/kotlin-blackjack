package blackjack.model

data class Player(val name: String, val bet: Bet) : Playable() {
    fun earnings(result: EarningsResult): Int {
        return result.earnings(bet)
    }
}
