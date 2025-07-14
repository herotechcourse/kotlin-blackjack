package blackjack.model

import blackjack.states.Running

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
            while (it.state is Running && askForCard(it)) {
                it.draw(cardDeck.drawCard())
                output(it)
            }
        }
    }
}
