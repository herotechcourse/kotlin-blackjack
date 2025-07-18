package blackjack.controller

import blackjack.model.Deck
import blackjack.model.FinalResult
import blackjack.model.Player
import blackjack.service.GameOrchestrator
import blackjack.service.InputProcessor
import blackjack.view.OutputView

class Controller {
    private val inputProcessor = InputProcessor()
    private val gameOrchestrator = GameOrchestrator(Deck(), inputProcessor)

    fun run() {
        try {
            val players = createPlayers()
            OutputView.displayNamesOfPlayers(players)
            val dealer = createDealer()
            roundOne(dealer, players)
            roundTwo(dealer, players)
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    internal fun createPlayers(): List<Player> {
        return inputProcessor
            .processPlayerNames()
            .map { Player(it, inputProcessor.processBetAmount(it)) }
    }

    fun createDealer(): Player {
        return Player(DEALER)
    }

    private fun roundOne(
        dealer: Player,
        players: List<Player>,
    ) {
        gameOrchestrator.dealInitialCards(dealer, players)
    }

    private fun roundTwo(
        dealer: Player,
        players: List<Player>,
    ) {
        OutputView.displayCardsOfDealer(dealer)
        players.forEach(OutputView::displayCardsOfPlayers)
        println()
        players.forEach(gameOrchestrator::runPlayerTurn)
        gameOrchestrator.runDealerTurn(dealer)
        OutputView.displayCardsOfPlayersWithScore(dealer)
        players.forEach(OutputView::displayCardsOfPlayersWithScore)
        val finalResult = FinalResult(dealer, players)
        finalResult.updateEarnings()
        OutputView.displayFinalResult(dealer, players)
    }

    companion object {
        const val DEALER = "dealer"
        const val BLACKJACK_SCORE = 21
    }
}
