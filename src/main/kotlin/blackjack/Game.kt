package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

class Game {

    fun run() {
        val playerNames = InputView.askPlayerNames()
        val players = playerNames.map { Player(it) }
        val dealer = Dealer()

        OutputView.displayDealing(playerNames)

        repeat(2) {
            dealer.giveCardTo(dealer)
            players.forEach { player ->
                dealer.giveCardTo(player)
            }
        }

        OutputView.displayDealerFirstTwoCards(dealer.getHand().take(2))
        OutputView.displayPlayerHands(players)

        OutputView.displayLineBreak()

        players.forEach { dealer.askPlayerDraw(it) }

        OutputView.displayLineBreak()
        OutputView.displayPlayerHands(players)

        dealer.playDealerTurn()

        OutputView.displayLineBreak()
        OutputView.displayDealerStatus(dealer)
        players.forEach { OutputView.displayPlayerStatus(it) }

        val dealerScore = ScoreCalculator.calculate(dealer)
        val isDealerBusted = ScoreCalculator.isBusted(dealer)

        var dealerWins = 0
        var dealerLosses = 0

        OutputView.displayFinalResultsHeader()
        players.forEach { player ->
            val playerScore = ScoreCalculator.calculate(player)
            val isPlayerBusted = ScoreCalculator.isBusted(player)

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
