package blackjack.model

object PlayerFactory {
    fun createPlayers(names: List<String>) = names.map { Player(it) }

    fun createDealer() = Player(DEALER)

    const val DEALER = "Dealer"
}
