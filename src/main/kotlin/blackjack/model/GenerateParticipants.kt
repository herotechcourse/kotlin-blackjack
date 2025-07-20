package blackjack.model

object GenerateParticipants {
    fun generatePlayers(names: List<String>): List<Player> {
        return names.map { Player(it) }
    }

    fun generateDealer(): Dealer {
        return Dealer()
    }
}
