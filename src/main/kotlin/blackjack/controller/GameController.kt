package blackjack.controller

import blackjack.controller.GameLogic
import blackjack.model.GameResult
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController {

    fun run() {
        val bettingInfos = GameLogic.getBettingInfo()
        val players = bettingInfos.map { Player(it.playerName) }
        val dealer = Dealer()

        OutputView.displayDealing(bettingInfos.map { it.playerName })

        dealer.giveTwoCardsTo(players)

        OutputView.displayDealerFirstTwoCards(dealer.getFirstTwoCards())
        OutputView.displayPlayerHands(players)

        OutputView.displayLineBreak()

        players.forEach { dealer.askPlayerDraw(it) }

        OutputView.displayLineBreak()
        OutputView.displayPlayerHands(players)

        dealer.playDealerTurn()

        OutputView.displayLineBreak()
        OutputView.displayDealerStatus(dealer)
        players.forEach { OutputView.displayPlayerStatus(it) }

        val dealerScore = dealer.getScore()
        val isDealerBusted = dealer.isBusted()

        var dealerWins = 0
        var dealerLosses = 0

        OutputView.displayFinalResultsHeader()
        players.forEach { player ->
            val playerScore = player.getScore()
            val isPlayerBusted = player.isBusted()

            val result = GameLogic.getGameResult(playerScore, dealerScore, isPlayerBusted, isDealerBusted)

            when (result) {
                GameResult.WIN -> dealerLosses++
                GameResult.LOSE -> dealerWins++
                GameResult.DRAW -> {}
            }

            OutputView.displayPlayerResult(player.name, result.label)
        }

        OutputView.displayDealerResult(dealerWins, dealerLosses)
    }
}