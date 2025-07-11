package blackjack.controller

import blackjack.model.*
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    private var players: List<Player> = mutableListOf()
    private val dealer: Player = Player(GamblerInfo("dealer"))
    private val deck = Deck()

    fun run() {
        try {
            createPlayers()
            roundOne()
            roundTwo()
            showResults(FinalResult(dealer, players))
        } catch (err: IllegalArgumentException) {
            OutputView.displayErrorMessages(err.message)
        }
    }

    private fun createPlayers() {
        players =
            processPlayerNames().map { Player(it) }
        OutputView.displayNamesOfPlayers(players)
    }

    private fun roundOne() {
        dealer.addCard(getInitialCards())
        players.forEach { it.addCard(getInitialCards()) }
        OutputView.displayCardsOfDealer(dealer)
        OutputView.displayCardsOfPlayers(players)
    }

    private fun roundTwo() {
        players.forEach { playerTakesTurn(it) }
        dealerTakesTurn()
        OutputView.displayCardsOfPlayersWithScore(listOf(dealer) + players)
        OutputView.displayFinalResultsHeading()
    }

    private fun getInitialCards(): List<Card> = deck.drawCards(INITIAL_CARD_COUNT)

    private fun dealerTakesTurn() {
        while (dealer.score <= DEALER_MIN_SCORE) {
            dealer.addCard(deck.drawCards())
            OutputView.displayDealersTurn()
        }
    }

    private fun playerTakesTurn(player: Player) {
        var answer = false
        while (player.score <= BLACKJACK_SCORE) {
            answer = processHitOrStay(player)
            if (!answer) {
                break
            }
            player.addCard(deck.drawCards())
            OutputView.displayCardsOfPlayers(player)
        }
        if (!answer && player.cards.size == INITIAL_CARD_COUNT) {
            OutputView.displayCardsOfPlayers(player)
        }
    }

    private fun showResults(finalResult: FinalResult) {
        OutputView.displayPlayerResult(
            finalResult.lose.size,
            finalResult.win.size,
            finalResult.draw.size,
        )
        finalResult.win.forEach {
            OutputView.displayPlayerResult(it.name, true)
        }
        finalResult.lose.forEach {
            OutputView.displayPlayerResult(it.name, false)
        }
        finalResult.draw.forEach {
            OutputView.displayPlayerResult(it.name, false)
        }
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

    private fun isHitOrStand(input: String): Boolean {
        return when (input) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("The answer must be y or n.")
        }
    }

    companion object {
        private const val MAX_ATTEMPTS = 5
        const val BLACKJACK_SCORE = 21
        const val DEALER_MIN_SCORE = 16
        const val INITIAL_CARD_COUNT = 2
        private const val MAX_ATTEMPT_MESSAGE = "Too many attempts"
    }
}
