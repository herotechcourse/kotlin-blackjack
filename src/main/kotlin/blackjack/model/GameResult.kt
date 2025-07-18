package blackjack.model

enum class GameResult(
    val label: String,
    private val multiplier: Double,
) {
    BLACKJACK_WIN("Blackjack Win", 1.5),
    WIN("Win", 1.0),
    DRAW("Draw", 0.0),
    LOSE("Lose", -1.0);

    fun calculateEarnings(bet: Int): Int {
        return (bet * multiplier).toInt()
    }
}

