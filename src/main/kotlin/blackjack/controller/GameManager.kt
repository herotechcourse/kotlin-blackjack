package blackjack.controller

import blackjack.view.InputView
import blackjack.view.OutputView

object GameManager {
    val deck = Deck()
    val playerManager = PlayerManager()
    val dealerManager = DealerManager()
    val statsManager = StatsManager(playerManager.players, dealerManager.dealer)
    val inputView = InputView
    val outputView = OutputView

    fun run() {
        takePlayerNames()
        dealerManager.dealer.giveInitCardsToPlayers(playerManager.players, deck)
        dealerManager.dealer.selfDrawInitCards(deck)
        OutputView.displayInitialState(playerManager.players, dealerManager.dealer)
        askPlayersToHit()
        drawDealerCards()
        OutputView.displayFinalState(playerManager.players, dealerManager.dealer)

        statsManager.processStatistics(playerManager.players, dealerManager.dealer)

        OutputView.displayFinalResults(statsManager.winStatistics, dealerManager.dealer)
    }

    private fun takePlayerNames() {
        val names = inputView.retryable { InputView.readPlayerNames() }
        names.forEach { name -> playerManager.addPlayer(name) }
    }

    private fun askPlayersToHit() {
        playerManager.players.forEach { player ->
            playerManager.askPlayerHit(player) { deck.drawCard() }
        }
    }

    private fun drawDealerCards() {
        while (dealerManager.dealer.shouldDrawCardOrNot()) {
            dealerManager.dealer.drawCard(deck.drawCard())
            OutputView.displayDealerDrawsCard(dealerManager.dealer)
        }
    }
}
