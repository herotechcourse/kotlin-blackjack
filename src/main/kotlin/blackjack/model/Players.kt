package blackjack.model

data class Players(private val players: List<Player>) {
    fun forEach(action: (Player) -> Unit) = players.forEach(action)

    fun toList(): List<Player> = players
}
