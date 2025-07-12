package blackjack.model.participant

import blackjack.model.holder.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {

    @Test
    fun `Participant sub classes execute card exchange logic with Deck`() {
        val deck = Deck()
        val player = Player("mina")
        val card = deck.draw()

        player.receive(card)
        assertThat(player.peekLast()).isEqualTo(card)
        assertThat(player.peekLast()).isSameAs(card)
    }
}