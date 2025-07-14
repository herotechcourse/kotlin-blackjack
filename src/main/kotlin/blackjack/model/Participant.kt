package blackjack.model

import blackjack.states.State

abstract class Participant(
    val name: String,
    var state: State,
)
