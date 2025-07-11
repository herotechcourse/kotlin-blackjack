package blackjack.model

object PlayerFactory {
    fun createPlayers(names: List<String>) = names.map { PlayerBackup(it) }

    fun createDealer() = PlayerBackup(DEALER)

    const val DEALER = "Dealer"
}
