package blackjack.model

import blackjack.state.Finished

class ParticipantResult {
    fun calculateEarningsRate(
        firstParticipant: Participant,
        secondParticipant: Participant,
    ): Double {
        if (firstParticipant.state !is Finished || secondParticipant.state !is Finished) {
            throw IllegalArgumentException("The participants must be finished!")
        }
        return (firstParticipant.state as Finished).earningsAgainst(secondParticipant.state as Finished)
    }
}
