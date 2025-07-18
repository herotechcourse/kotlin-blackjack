package blackjack.controller

import blackjack.model.Bet
import blackjack.model.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

object GameManager {
    val deck = Deck()
    val playerManager = PlayerManager()
    val dealer = Dealer()
    val statsManager = StatsManager(playerManager.players, dealer)
    val inputView = InputView
    val outputView = OutputView

    fun run() {
        takePlayerNames()
        dealer.giveInitCardsToPlayers(playerManager.players, deck)
        dealer.selfDrawInitCards(deck)
        OutputView.displayInitialState(playerManager.players, dealer)
        askPlayersToHit()
        drawDealerCards()
        OutputView.displayFinalState(playerManager.players, dealer)

        statsManager.processStatistics(playerManager.players, dealer)

        OutputView.displayFinalResults( statsManager.earnings)
    }

    private fun takePlayerNames() {
        val names = inputView.retryable { InputView.readPlayerNames() }
        names.forEach { name ->
            val betAmount = inputView.retryable { inputView.readBetAmount(name) }
            val bet = Bet(betAmount)
            playerManager.addPlayer(name, bet)
        }
    }

    private fun askPlayersToHit() {
        playerManager.players.forEach { player ->
            playerManager.askPlayerHit(player) { deck.drawCard() }
        }
    }

    private fun drawDealerCards() {
        while (dealer.shouldDrawCardOrNot()) {
            dealer.drawCard(deck.drawCard())
            OutputView.displayDealerDrawsCard()
        }
    }
}
