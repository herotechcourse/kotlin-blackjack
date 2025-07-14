package blackjack.model

class Players (private val players : List<Player>) {
    init {
        require(players.isNotEmpty()) { "Player list must not be empty" }
        require(players.size <= 7) { "Maximum number of players must be 7" }
    }

    fun getPlayers() = players

    fun calculateTotalPlayersEarning() = players.sumOf { player -> player.earnings }
}
