package blackjack.model.table

import blackjack.model.states.State

abstract class Participant {
    abstract val name: String
    abstract var state: State
}
