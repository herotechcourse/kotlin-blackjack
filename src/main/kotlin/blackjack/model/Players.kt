package blackjack.model

class Players(val players: List<Player>) {
    init {
        require(players.size in MIN..MAX) { ErrorMessage.PLAYER_RANGE.message }
    }

    fun forEach(func: (Player) -> Unit) {
        players.forEach { player -> func(player) }
    }

    companion object {
        const val MIN = 1
        const val MAX = 6
    }
}
