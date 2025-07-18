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
        players.forEach { it.dealInitialCards(cardDeck) }
        dealer.dealInitialCards(cardDeck)
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
