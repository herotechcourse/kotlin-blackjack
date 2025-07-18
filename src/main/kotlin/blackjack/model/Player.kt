package blackjack.model

open class Player(
    name: String,
    val bet: Bet,
    var earnings: Int = 0,
) : Participant(name)
