package blackjack.model

data class Player(override val name: String, val bet: Bet) : Playable() {
    fun earnings(result: EarningsResult): Int {
        return result.earnings(bet)
    }
}
