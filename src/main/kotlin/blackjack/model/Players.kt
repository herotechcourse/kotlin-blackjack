package blackjack.model

class Players(
    val values: List<Player>
) {
    init {
        require(values.isNotEmpty()) { "Player list must not be empty" }
        require(values.size <= 7) { "Maximum number of players must be 7" }
    }

    fun calculateTotalPlayersEarning() = values.sumOf { player -> player.earnings }
}
