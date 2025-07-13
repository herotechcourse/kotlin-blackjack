package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.ResultEvaluator
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.OutputView.displayInitialCardsMessage

class Controller {
    private val deck = CardDeck()
    private var _players: Players? = null
    private lateinit var dealer: Dealer

    private val players: Players
            get() = _players!!

    fun runGame() {
        _players = initializePlayers()
        dealer = Dealer(deck = deck, players = players)
        OutputView.displayPlayerNames(players)
        drawInitialCards()
        OutputView.displayFirstCardMessage(dealer)
        players.forEach { OutputView.displayAllCardsMessage(it) }
        dealer.serviceParticipants()
        displayCardsAndTotal()
        printResults()
    }

    private fun printResults() {
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        OutputView.displayResults(results)
    }

    private fun displayCardsAndTotal() {
        OutputView.displayParticipantStatus(dealer)
        players.forEach { OutputView.displayParticipantStatus(it) }
    }

    private fun initializePlayers(): Players {
        val playerNames = InputView.readNames()
        return Players(playerNames.map { Player(it) })
    }

    private fun drawInitialCards() {
        displayInitialCardsMessage(players)
        repeat(2) {
            dealer.drawCard(deck)
            players.forEach { it.drawCard(deck) }
        }
    }
}
