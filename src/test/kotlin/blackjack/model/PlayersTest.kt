package blackjack.model
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `should return list of players`() {
        val players = Players(listOf(Player("Player1"), Player("Player2")))
        assertEquals(2, players.toList().size)
    }
}
