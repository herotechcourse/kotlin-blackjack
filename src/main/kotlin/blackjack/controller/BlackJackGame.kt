package blackjack.controller

import blackjack.model.participant.Participants
import blackjack.view.InputView
import kotlin.system.exitProcess

object BlackJackGame {
    fun run() {
        runCatching {
            val names = InputView.getPlayersName()
            val participants = Participants.from(names)

        }.onFailure { exception ->
            OutputView.PrintError(exception)
            exitProcess(1)
        }
    }
}
