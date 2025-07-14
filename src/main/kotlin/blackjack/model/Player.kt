package blackjack.model

import blackjack.states.FirstTurn

class Player(name: String) : Participant(name, FirstTurn(Hand(emptyList())))
