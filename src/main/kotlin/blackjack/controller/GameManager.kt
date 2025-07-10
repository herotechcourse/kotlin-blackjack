package blackjack.controller

import blackjack.view.InputView
import blackjack.view.OutputView

object GameManager {
    val cardManager = CardManager()
    val playerManager = PlayerManager()
    val dealerManager = DealerManager()
    val statsManager = StatsManager(playerManager.players, dealerManager.dealer)
    val inputView = InputView
    val outputView = OutputView

    fun run() {
        takePlayerNames()
        initHands()
        OutputView.displayInitialState(playerManager.players, dealerManager.dealer)
        askPlayersToHit()
        drawDealerCards()
        OutputView.displayFinalState(playerManager.players, dealerManager.dealer)
        statsManager.processStatistics(playerManager.players, dealerManager.dealer)
        OutputView.displayFinalResults(statsManager.winStatistics)
    }

    private fun takePlayerNames() {
        val names = inputView.retryable { InputView.readPlayerNames() }
        names.forEach { name -> playerManager.addPlayer(name) }
    }

    private fun initHands() {
        repeat(2) {
            playerManager.players.forEach { player ->
                player.drawCard(cardManager.giveCard())
            }
            dealerManager.dealer.drawCard(cardManager.giveCard())
        }
    }

    // TODO: extract InputView from 'askPlayerHit()'
    private fun askPlayersToHit() {
        playerManager.players.forEach { player ->
            playerManager.askPlayerHit(player) { cardManager.giveCard() }
        }
    }

    private fun drawDealerCards() {
        while (dealerManager.dealer.shouldDrawCardOrNot()) {
            dealerManager.dealer.drawCard(cardManager.giveCard())
            OutputView.displayDealerDrawsCard()
        }
    }
}
