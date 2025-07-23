package blackjack.controller

import blackjack.Constants.DEALER
import blackjack.model.Deck
import blackjack.model.FinalResult
import blackjack.model.Player
import blackjack.model.Players
import blackjack.service.GameOrchestrator
import blackjack.service.InputProcessor
import blackjack.view.OutputView

class Controller {
    private val inputProcessor = InputProcessor()
    private val gameOrchestrator = GameOrchestrator(Deck(), inputProcessor)

    fun run() {
        try {
            val players = createPlayers()
            val dealer = createDealer()
            setup(players)
            executeGameRounds(dealer, players)
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    private fun createPlayers(): List<Player> {
        return inputProcessor
            .processPlayerNames()
            .map { Player(it, inputProcessor.processBetAmount(it)) }
    }

    private fun createDealer(): Player {
        return Player(DEALER)
    }

    private fun setup(players: List<Player>) {
        OutputView.displayNamesOfPlayers(players)
    }

    private fun executeGameRounds(
        dealer: Player,
        players: List<Player>,
    ) {
        val gameRounds =
            listOf(
                ::initialDealRound,
                ::playerActionsRound,
            )
        gameRounds.forEach { round -> round(dealer, players) }
    }

    private fun initialDealRound(
        dealer: Player,
        players: List<Player>,
    ) {
        gameOrchestrator.dealInitialCards(dealer, players)
        OutputView.displayCardsOfDealer(dealer)
        players.forEach(OutputView::displayCardsOfPlayers)
    }

    private fun playerActionsRound(
        dealer: Player,
        players: List<Player>,
    ) {
        players.forEach(gameOrchestrator::runPlayerTurn)
        gameOrchestrator.runDealerTurn(dealer)
        OutputView.displayCards(dealer, players)
        FinalResult(dealer, Players(players)).updateEarnings()
        OutputView.displayFinalResult(dealer, players)
    }
}
