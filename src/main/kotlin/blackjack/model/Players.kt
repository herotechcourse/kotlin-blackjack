package blackjack.model

class Players(
    val members: List<Player>
) {
    init {
        require(members.isNotEmpty()) { "Player list must not be empty" }
        require(members.size <= 7) { "Maximum number of players must be 7" }
    }

    fun calculateTotalPlayersEarning() = members.sumOf { player -> player.earnings }
}
