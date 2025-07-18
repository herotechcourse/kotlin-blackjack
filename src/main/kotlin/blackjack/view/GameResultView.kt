package blackjack.view

import blackjack.model.GameResult

enum class GameResultView(
    private val result: GameResult,
    val label: String,
) {
    BLACKJACK_WIN(GameResult.BLACKJACK_WIN, "Blackjack Win"),
    WIN(GameResult.WIN, "Win"),
    DRAW(GameResult.DRAW, "Draw"),
    LOSE(GameResult.LOSE, "Lose"),
    ;

    companion object {
        fun from(result: GameResult): GameResultView {
            return entries.first { it.result == result }
        }
    }
}
