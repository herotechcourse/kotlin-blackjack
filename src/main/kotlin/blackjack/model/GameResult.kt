package blackjack.model

enum class GameResult(val calculateEarnings: (Bet) -> Int) {
    BLACKJACK_WIN({ bet -> (bet.amount * 1.5).toInt() }),
    WIN({ bet -> bet.amount }),
    DRAW({ _ -> 0 }),
    LOSE({ bet -> -bet.amount }),
    ;

    fun earningsFrom(bet: Bet): Int {
        return calculateEarnings(bet)
    }
}
