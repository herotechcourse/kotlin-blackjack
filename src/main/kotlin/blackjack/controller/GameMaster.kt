package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.PlayingCard
import blackjack.model.Stats
import blackjack.view.InputView
import blackjack.view.OutputView

object GameMaster {
    val deck = PlayingCard.deck
    val playerManager = PlayerManager()
    val dealer = Dealer()

    fun run() {
        PlayingCard.deck.shuffle()
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
                player.drawCard(deck.giveCard())
            }
            dealer.drawCard(deck.giveCard())
        }
    }

    private fun askPlayersToHit() {
        playerManager.players.forEach { player ->
            playerManager.askPlayerHit(player) { deck.giveCard() }
        }
    }

    private fun drawDealerCards() {
        while (dealer.shouldDrawCardOrNot()) {
            dealer.drawCard(deck.giveCard())
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
