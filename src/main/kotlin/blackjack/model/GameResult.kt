package blackjack.model

enum class GameResult(
    private val multiplier: Double,
) {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    fun calculateEarnings(bet: Int): Int {
        return (bet * multiplier).toInt()
    }
}
