package blackjack.model.participant

import blackjack.TestFixture.last
import blackjack.model.holder.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `Participant sub classes execute card exchange logic with Deck`() {
        val deck = Deck()
        val player = Player("mina")
        val card = deck.draw(1)

        player.receive(card)
        assertThat(player.last()).isEqualTo(card[0])
        assertThat(player.last()).isSameAs(card[0])
    }
}
