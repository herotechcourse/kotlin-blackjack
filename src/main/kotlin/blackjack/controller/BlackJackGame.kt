package blackjack.controller

import blackjack.model.participant.Participants
import blackjack.view.InputView
import blackjack.view.OutputView
import kotlin.system.exitProcess

object BlackJackGame {
    fun run() {
        runCatching {
            val names = InputView.getPlayersName()
            val participants = Participants.from(names)

        }.onFailure { exception ->
            OutputView.printError(exception.message)
            exitProcess(1)
        }
    }
}
