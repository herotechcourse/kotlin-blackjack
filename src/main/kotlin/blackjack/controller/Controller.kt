package blackjack.controller

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Gambler
import blackjack.model.GamblerInfo
import blackjack.model.Player
import blackjack.model.PlayerBet
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    private val players: MutableList<Gambler> = mutableListOf()
    private val dealer: Dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
    private val deck = Deck()

    fun run() {
        try {
            createPlayers()
            firstDraw()
            finalDraw()
            calculateAndShowWinnings()
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    private fun createPlayers() {
        val names = processPlayerNames()
        OutputView.displayNamesOfPlayers(names)
        val playersToAdd =
            names.map {
                val bet = processBetAmount(it)
                Gambler(GamblerInfo(it), PlayerBet(bet))
            }
        players.addAll(playersToAdd)
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

    private fun processPlayerNames(): List<String> {
        repeat(MAX_ATTEMPTS) {
            try {
                val names = InputView.getNamesOfPlayers()
                return names.parseCommaString()
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
