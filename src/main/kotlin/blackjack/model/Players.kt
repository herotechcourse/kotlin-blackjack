package blackjack.model

class Players(private val playerList: List<Player>) {
    fun forEach(action: (Player) -> Unit) {
        playerList.forEach(action)
    }

    val size: Int get() = playerList.size

    operator fun iterator(): Iterator<Player> = playerList.iterator()
}
