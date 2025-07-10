package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.ErrorMessage
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.ResultEvaluator
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.OutputView.displayInitialCardsMessage

class Controller {
    private val deck = CardDeck()
    private var _players: Players? = null
    private val dealer = Dealer()

    private val players: Players
            get() = _players!!

    fun runGame() {
        _players = initializePlayers()
        OutputView.displayPlayerNames(players)
        drawInitialCards()
        OutputView.displayFirstCardMessage(dealer)
        players.forEach { OutputView.displayAllCardsMessage(it) }
        players.dealCards()
        dealer.dealCards()
        displayCardsAndTotal()
        printResults()
    }

    fun printResults() {
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        OutputView.displayResults(results)
    }

    fun displayCardsAndTotal() {
        OutputView.displayParticipantStatus(dealer)
        players.forEach { OutputView.displayParticipantStatus(it) }
    }

    fun Dealer.dealCards() {
        while (this.shouldDraw()) {
            OutputView.displayDealerDrawMessage()
            this.drawCard(deck)
        }
    }

    fun Players.dealCards() {
        players.forEach { dealCards(it) }
    }

    fun dealCards(player: Player) {
        while (player.isNotBusted() && wantsToDraw(player)) {
            player.drawCard(deck)
            OutputView.displayAllCardsMessage(player)
        }
    }

    fun wantsToDraw(player: Player): Boolean {
        repeat(MAX_TRIES){
            try {
                return InputView.promptForDraw(player)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                return@repeat
            }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.message)
    }

    fun initializePlayers(): Players {
        repeat(MAX_TRIES) {
            try {
                val playerNames = InputView.readNames()
                val players = Players(playerNames.map { Player(it) })
                return players
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.message)
    }

    fun drawInitialCards() {
        displayInitialCardsMessage(players)
        repeat(2) {
            dealer.drawCard(deck)
            players.forEach { it.drawCard(deck) }
        }
    }

    companion object {
        const val MAX_TRIES = 5
    }
}
