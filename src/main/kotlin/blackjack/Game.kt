package blackjack

class Game {
    fun run() {
        val playerNames = InputView.askPlayerNames()
        val players = playerNames.map { Player(it) }
        val dealer = Dealer()
    }
}