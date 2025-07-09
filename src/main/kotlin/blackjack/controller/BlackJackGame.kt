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

        val answer = InputView.getAnswer()


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
}