package blackjack

import blackjack.controller.GameManager
import blackjack.factory.PlayerFactory
import blackjack.model.participant.Participants
import blackjack.model.result.GameResult
import blackjack.view.InputView
import blackjack.view.OutputView
import kotlin.system.exitProcess

fun main() {
    runCatching {
        val names = InputView.readPlayersNames()
        val bets = InputView.readPlayersBet(names)

        val players = PlayerFactory(names, bets).createPlayers()
        val participants = Participants(players)

        GameManager(participants).play()

        val gameResult = GameResult(participants)
        OutputView.showGameResult(gameResult)
    }.onFailure { exception ->
        OutputView.showError(exception.message)
        exitProcess(1)
    }
}
