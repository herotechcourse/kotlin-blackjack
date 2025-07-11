package blackjack.controller

import blackjack.model.participant.Participants
import blackjack.view.InputView
import blackjack.view.OutputView
import kotlin.system.exitProcess

object BlackJackGame {
    fun play() {
        runCatching {
            val names = InputView.getPlayersName()
            val participants = Participants.from(names)

        }.onFailure { exception ->
            OutputView.PrintError(exception)
            exitProcess(1)
        }
    }
}
