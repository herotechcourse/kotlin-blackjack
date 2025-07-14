package blackjack.model

abstract class Participant(
    val name: String,
    val cardsInHand: Cards = Cards(mutableListOf())
) {
    var isPlaying: Boolean = true
        private set

    var earnings: Int = 0
        private set

    fun drawCard(card: Card) {
        cardsInHand.addCard(card)
    }

    fun updatePlayingStatus(condition: Boolean) {
        if (condition)
            isPlaying = false
    }

    fun updateEarnings(newEarnings: Int) {
        earnings += newEarnings
    }
}
