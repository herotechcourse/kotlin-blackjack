package blackjack.model

import blackjack.utils.Constants

data class Player(override val name: String, val bet: Bet) : Playable() {
    fun earnings(result: Int): Int {
        return when (result) {
            Constants.WIN -> bet.amount
            Constants.WIN_BLACK_JACK -> (bet.amount * 1.5).toInt()
            Constants.TIE -> 0
            Constants.LOSE -> -bet.amount
            else -> throw IllegalArgumentException("Invalid result")
        }
    }
}
