package blackjack.model

import blackjack.states.State

abstract class Participant(
    val name: String,
    val state: State,
)
