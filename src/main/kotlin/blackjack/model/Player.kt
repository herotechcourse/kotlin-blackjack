package blackjack.model

import blackjack.utils.Constants

data class Player(override val name: String, val bet: Bet) : Playable() {
    fun earnings(result: EarningsResult): Int {
        return result.earnings(bet)
    }
}
