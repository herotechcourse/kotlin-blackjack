import model.BlackJack
import model.Dealer
import model.Player
import service.ResultCalculator
import view.OutputView

fun main() {
    val players = BlackJack.getPlayerNames().map { Player(it) }
    val dealer = Dealer()
    BlackJack.initGame(players, dealer)
    OutputView.displayInitialCards(players, dealer)
    BlackJack.runGame(players, dealer)
    OutputView.displayFinalCardsOnHand(players, dealer)
    OutputView.displayResults(
        ResultCalculator.getResult(
            players.map { it.getScore() },
            dealer.getScore(),
        ),
        players,
    )
}
