package blackjack.controller

import blackjack.model.participant.Participants
import blackjack.model.result.GameResult
import blackjack.view.InputView
import blackjack.view.OutputView
import kotlin.system.exitProcess

object BlackJackGame {
    fun run() {
        runCatching {
            val names = InputView.readPlayersNames()
            val participants = Participants.from(names)

            GameManager(participants).play()

            val gameResult = GameResult(participants)
            OutputView.showGameResult(gameResult)
        }.onFailure { exception ->
            OutputView.showError(exception.message)
            exitProcess(1)
        }
    }
}
