import model.BackJack
import model.Dealer
import model.Player
import service.ResultCalculator
import view.OutputView

fun main() {
    val players = BackJack.getPlayerNames().map { Player(it) }
    val dealer = Dealer()
    BackJack.initGame(players, dealer)
    OutputView.displayInitialCards(players, dealer)
    BackJack.runGame(players, dealer)
    OutputView.displayFinalCardsOnHand(players, dealer)
    OutputView.displayResults(
        ResultCalculator.getResult(
            players.map { it.getScore() },
            dealer.getScore(),
        ),
        players,
    )
}
