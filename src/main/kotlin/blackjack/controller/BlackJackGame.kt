package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame {
    val dealer = Dealer()
    var players = listOf<Player>()

    fun start() {
        createPlayers()
        dealFirstCards()
        dealingPlayersCards()
        dealingDealersCards()
        calculateResults()
    }

    fun createPlayers() {
        players = InputView.getPlayersNames().map { Player(it) }
    }

    fun dealFirstCards() {
        repeat(2) {
            dealer.addCard(dealer.dealCard())
            players.forEach { it.addCard(dealer.dealCard()) }
        }
        OutputView.printParticipantsHands(players, dealer)
    }

    fun dealingPlayersCards() {
        for (player in players) {
            while (!player.hasBlackJack() && !player.isBusts()) {
                val answer = InputView.getAnswer(player.name)
                if (answer == "y") {
                    val card = dealer.dealCard()
                    player.addCard(card)
                    println(player)
                } else {
                    break
                }
            }
        }
    }

    fun dealingDealersCards() {
        while (dealer.shouldNotStand()) {
            dealer.addCard(dealer.dealCard())
            OutputView.printDealersMessage()
        }
        dealer.showAllCards()
    }

    fun calculateResults() {
        OutputView.printFinalHands(players, dealer)

        players.forEach { dealer.setResultFor(it) }

        OutputView.printResults(players, dealer)
    }
}
