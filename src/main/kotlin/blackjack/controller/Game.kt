package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Game(
    private val dealer: Dealer,
) {
    fun startGame() {
        val playersName = InputView.askPlayerNames()
        val players = playersName.map { Player(it) }

        collectBets(players)

        assignInitialCards(players)

        hitOrStay(players)

        compareFinalCards(players)
    }

    private fun collectBets(players: List<Player>) {
        players.forEach { player ->
            player.setBet(InputView.askPlayerBet(player.name))
        }
    }

    private fun assignInitialCards(players: List<Player>) {
        dealer.selfDrawInitialCards()
        dealer.dealInitialCardsToPlayers(players)
        OutputView.displayInitialCards(dealer, players)
    }

    private fun hitOrStay(players: List<Player>) {
        askPlayersToHit(players)
        dealerDraws()
        OutputView.displayCardsWithTotalValue(dealer)
        players.forEach { player -> OutputView.displayCardsWithTotalValue(player) }
    }

    private fun askPlayersToHit(players: List<Player>) {
        players.forEach {
            do {
                val answer = InputView.askToHit(it.name)
                if (answer) {
                    dealer.dealCardToPlayer(it)
                    it.updatePlayingStatus(it.cardsInHand.checkIfIsBustHand())
                    println(OutputView.showHandCards(it, false))
                }
            } while (answer && it.isPlaying)
        }
    }

    private fun dealerDraws() {
        var mustDraw = dealer.mustDraw(dealer.cardsInHand.calculateTotalValueOfCards())
        while (mustDraw) {
            OutputView.displayDealerDrawMessage(dealer)
            dealer.selfDrawCard()
            mustDraw = dealer.mustDraw(dealer.cardsInHand.calculateTotalValueOfCards())
        }
        dealer.updatePlayingStatus(dealer.cardsInHand.checkIfIsBustHand())
    }

    private fun compareFinalCards(players: List<Player>) {
        if (dealer.isPlaying) {
            val dealerPoints = dealer.cardsInHand.calculateTotalValueOfCards()
            players.forEach {
                it.updatePlayingStatus(it.hasLessPointsThanDealer(dealerPoints))
            }
        }
        OutputView.displayFinalResults(dealer, players)
    }
}
