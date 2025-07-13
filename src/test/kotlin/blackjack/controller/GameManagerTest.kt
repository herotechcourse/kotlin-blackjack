package blackjack.controller

import blackjack.model.participant.Participants
import blackjack.view.OutputView
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun setUp() {
        val participants = Participants.from(listOf("mina", "guri"))
        GameManager(participants).setUp()

        OutputView.showCards(participants.getDealer())
        OutputView.showAllPayersCards(participants)
    }
}