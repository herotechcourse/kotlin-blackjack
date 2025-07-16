package blackjack.model

enum class EarningsResult {
    LOSE_BET,
    WIN_BET,
    TIE_BET,
    WIN_BLACKJACK_BET;

    fun earnings(bet: Bet): Int = when (this) {
        WIN_BET -> bet.amount
        WIN_BLACKJACK_BET -> (bet.amount * 1.5).toInt()
        TIE_BET -> 0
        LOSE_BET -> -bet.amount
    }
}
