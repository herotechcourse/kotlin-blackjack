package blackjack.model

class Players(private val playerList: List<Player>) {
    fun forEach(action: (Player) -> Unit) {
        playerList.forEach(action)
    }

    fun calculateTotalEarnings(): Double {
        return playerList.sumOf { it.earning }
    }

    val size: Int get() = playerList.size

    operator fun iterator(): Iterator<Player> = playerList.iterator()
}
