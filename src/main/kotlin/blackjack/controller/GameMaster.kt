package blackjack.controller

import blackjack.model.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

object GameMaster {
    val cardManager = CardManager()
    val playerManager = PlayerManager()
    val dealer = Dealer()
    val statsManager = StatsManager()

    fun run() {
        takePlayerNames()
        initHands()
        OutputView.displayInitialState(playerManager.players, dealer)
        askPlayersToHit()
        drawDealerCards()
        OutputView.displayFinalState(playerManager.players, dealer)
        statsManager.processStatistics(playerManager.players, dealer)
        OutputView.displayFinalResults(statsManager.winStatistics)
    }

    private fun takePlayerNames() {
        val names = InputView.retryable { InputView.readPlayerNames() }
        names.forEach { name -> playerManager.addPlayer(name) }
    }

    private fun initHands() {
        repeat(2) {
            playerManager.players.forEach { player ->
                player.drawCard(cardManager.giveCard())
            }
            dealer.drawCard(cardManager.giveCard())
        }
    }

    private fun askPlayersToHit() {
        playerManager.players.forEach { player ->
            playerManager.askPlayerHit(player) { cardManager.giveCard() }
        }
    }

    private fun drawDealerCards() {
        while (dealer.shouldDrawCardOrNot()) {
            dealer.drawCard(cardManager.giveCard())
            OutputView.displayDealerDrawsCard()
        }
    }
}
