package blackjack.model

abstract class Participant(val name: String) {
    var score: Int = 0
    var aceCount: Int = 0

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }

    fun isBlackjack(): Boolean = score == BLACK_JACK

    fun isBusts(): Boolean = score > BLACK_JACK

    companion object {
       const val BLACK_JACK = 21
    }

//    fun isAce(card: Int): Boolean {
//        return card == 11
//    }
//
//    fun addCard(card: Int) {
//        if (isAce(card)) {
//            if (score + card > 21) {
//
//            }
//        }
//    }


}