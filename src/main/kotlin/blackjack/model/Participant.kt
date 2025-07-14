package blackjack.model

abstract class Participant(
    val name: String,
    val cardsInHand: Cards = Cards(mutableListOf())
) {
    var isPlaying: Boolean = true
        private set

    fun drawCard(card: Card) {
        cardsInHand.addCard(card)
    }

    fun updatePlayingStatus(condition : Boolean) {
        if (condition)
            isPlaying = false
    }
}
