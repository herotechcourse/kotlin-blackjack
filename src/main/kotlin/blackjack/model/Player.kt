package blackjack.model

class Player(name: String, var earnings: Int = 0, val bet: Int = 0) : Participant(name)
