package blackjack.model

open class Player(
    val name: String,
    val bet: Bet,
    var earnings: Int = 0,
) : Participant()
