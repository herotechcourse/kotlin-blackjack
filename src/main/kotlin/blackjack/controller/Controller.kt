package blackjack.controller

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Gambler
import blackjack.model.GamblerInfo
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    private var players: List<Gambler> = mutableListOf()
    private val dealer: Dealer = Dealer(GamblerInfo("Dealer"))
    private val deck = Deck()

    fun run() {
        try {
            createPlayers()
            readBetAmount()
            firstDraw()
            finalDraw()
            calculateAndShowWinnings()
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    private fun createPlayers() {
        players =
            processPlayerNames().map { Gambler(it) }
        OutputView.displayNamesOfPlayers(players)
    }

    private fun firstDraw() {
        dealer.addCard(getInitialCards())
        players.forEach { it.addCard(getInitialCards()) }
        OutputView.displayCardsOfDealer(dealer)
        OutputView.displayCardsOfPlayers(players)
    }

    private fun finalDraw() {
        players.forEach { playerTakesTurn(it) }
        dealerTakesTurn()
        OutputView.displayCardsOfPlayersWithScore(listOf(dealer) + players)
    }

    private fun readBetAmount() {
        players.forEach { it.setBetAmount(processBetAmount(it.name)) }
    }

    private fun getInitialCards(): List<Card> = deck.drawCards(INITIAL_CARD_COUNT)

    private fun dealerTakesTurn() {
        while (dealer.isDealerBelowMinScore()) {
            dealer.addCard(deck.drawCards())
            OutputView.displayDealersTurn()
        }
    }

    private fun playerTakesTurn(gambler: Gambler) {
        var answer = false
        while (gambler.isPlayerBelowBlackJack()) {
            answer = processHitOrStay(gambler)
            if (!answer) {
                break
            }
            gambler.addCard(deck.drawCards())
            OutputView.displayCardsOfPlayers(gambler)
        }
        if (!answer && gambler.hasCardCount()) {
            OutputView.displayCardsOfPlayers(gambler)
        }
    }

    private fun calculateAndShowWinnings() {
        players.forEach { it.setWinnings(dealer) }
        dealer.calculateAndSetWinnings(players.map { it.winnings })
        OutputView.displayFinalEarning(listOf(dealer) + players)
    }

    private fun processPlayerNames(): List<GamblerInfo> {
        repeat(MAX_ATTEMPTS) {
            try {
                val names = InputView.getNamesOfPlayers()
                return names
                    .parseCommaString()
                    .map(::GamblerInfo)
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    private fun processHitOrStay(player: Player): Boolean {
        repeat(MAX_ATTEMPTS) {
            try {
                return isHitOrStand(InputView.getHitOrStand(player.name))
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    private fun processBetAmount(name: String): Double {
        repeat(MAX_ATTEMPTS) {
            try {
                return InputView.getBetAmount(name)
            } catch (err: IllegalArgumentException) {
                OutputView.displayErrorMessages(err.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    private fun isHitOrStand(input: String): Boolean {
        return when (input) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("The answer must be y or n.")
        }
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
        private const val MAX_ATTEMPTS = 5
        private const val MAX_ATTEMPT_MESSAGE = "Too many attempts"
    }
}
