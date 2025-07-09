package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Participant
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
        OutputView.printFinalHands(players, dealer)

        val dealerScore = dealer.getScore()
        val dealerHasBlackJack = dealer.hasBlackJack()
        val dealerIsBusts = dealer.isBusts()

        for (player in players) {
            val playerScore = player.getScore()
            val playerHasBlackJack = player.hasBlackJack()
            val playerIsBusts = player.isBusts()

            when {
                playerIsBusts -> {
                    player.setLose()
                    dealer.setWin()
                }
                dealerIsBusts -> {
                    player.setWin()
                    dealer.setLose()
                }
                playerHasBlackJack && !dealerHasBlackJack -> {
                    player.setWin()
                    dealer.setLose()
                }
                playerScore == dealerScore -> {
                    player.setTie()
                    dealer.setTie()
                }
                playerScore < dealerScore -> {
                    player.setLose()
                    dealer.setWin()
                }
                else -> {
                    player.setWin()
                    dealer.setLose()
                }
            }
        }
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
                } else break
            }
        }
    }

    fun dealingDealersCards() {
        while (dealer.shouldNotStand()) {
            dealer.addCard(dealer.dealCard())
            OutputView.printDealersMessage()
        }
        dealer.showAll = true
    }
}
