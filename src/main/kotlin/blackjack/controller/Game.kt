package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.ResultCalculation
import blackjack.view.InputView
import blackjack.view.OutputView

class Game(
    private val dealer: Dealer,
) {
    fun startGame() {
        val playersName = InputView.askPlayerNames()
        val players = Players(playersName.map { Player(it) })

        collectBets(players)

        assignInitialCards(players)

        hitOrStay(players)

        compareFinalCards(players)
    }

    private fun collectBets(players: Players) {
        players.values.forEach { player ->
            player.placeBet(InputView.askPlayerBet(player.name))
        }
    }

    private fun assignInitialCards(players: Players) {
        dealer.selfDrawInitialCards()
        dealer.dealInitialCardsToPlayers(players)
        OutputView.displayInitialCards(dealer, players)
    }

    private fun hitOrStay(players: Players) {
        askPlayersToHit(players)
        dealerDraws()
        OutputView.displayDealersCardsWithTotalValue(dealer)
        players.values.forEach { player -> OutputView.displayCardsWithTotalValue(player) }
    }

    private fun askPlayersToHit(players: Players) {
        players.values.forEach {
            do {
                val answer = InputView.askToHit(it.name)
                if (answer) {
                    dealer.dealCardToPlayer(it)
                    OutputView.displayCardsAfterHit(it)
                }
            } while (answer && it.isAbleToHit())
        }
    }

    private fun dealerDraws() {
        var mustDraw = dealer.mustDraw(dealer.cardsInHand.calculateTotalValueOfCards())
        while (mustDraw) {
            OutputView.displayDealerDrawMessage()
            dealer.selfDrawCard()
            mustDraw = dealer.mustDraw(dealer.cardsInHand.calculateTotalValueOfCards())
        }
    }

    private fun compareFinalCards(players: Players) {
        calculateEarnings(players)
        OutputView.displayFinalResults(dealer, players)
    }

    private fun calculateEarnings(players: Players) {
        players.values
            .forEach { player -> player.receiveEarningsOrLoses(ResultCalculation.calculatePlayerResult(player, dealer)) }
        dealer.calculateBalance(players.calculateTotalPlayersEarning())
    }
}
