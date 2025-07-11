package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Stats
import blackjack.view.InputView
import blackjack.view.OutputView

object GameMaster {
    val cardManager = CardManager()
    val playerManager = PlayerManager()
    val dealer = Dealer()

    fun run() {
        takePlayerNames()
        initHands()
        OutputView.displayInitialState(playerManager.players, dealer)
        askPlayersToHit()
        drawDealerCards()
        OutputView.displayFinalState(playerManager.players, dealer)
        val winStatistics = calculateStatistics(playerManager.players, dealer)
        OutputView.displayFinalResults(winStatistics)
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

    private fun calculateStatistics(
        players: List<Player>,
        dealer: Dealer,
    ): Stats {
        val winStatistics = Stats(players, dealer)
        winStatistics.updateDealerStats()
        return winStatistics
    }
}
