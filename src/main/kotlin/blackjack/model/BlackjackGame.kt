package blackjack.model

class BlackjackGame(
    private val dealer: Dealer,
    private val players: List<Player>,
    private val askForCard: (Player) -> Boolean,
    private val output: (Player) -> Unit,
) {
    val cardDeck = CardDeck()

    init {
        setUpTable()
    }

    fun setUpTable() {
        players.forEach { player ->
            repeat(2) {
                player.state = player.state.draw(cardDeck.drawCard())
            }
        }
        repeat(2) { dealer.state = dealer.state.draw(cardDeck.drawCard()) }
    }

    fun play() {
        dealer.playTurn(cardDeck)
        players.forEach {
            while (it.state.isRunning() && askForCard(it)) {
                it.draw(cardDeck.drawCard())
                output(it)
            }
            if (it.state.isRunning()) it.stay()
        }
    }
}
