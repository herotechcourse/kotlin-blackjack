package blackjack.model

data class PlayerResult(private val player: Player) {
    val name = player.name
    val cards = player.cards
    val points = player.calculatePoints()
}
