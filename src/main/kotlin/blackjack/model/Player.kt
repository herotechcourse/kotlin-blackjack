package blackjack.model

class Player(
    name: String,
    val bet: Int = 0,
    var earnings: Int = 0,
) : Participant(name)
