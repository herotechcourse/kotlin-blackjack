package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackJackTest {
    @Test
    fun `should throw exception if more than 6 players`() {
        val names = listOf("A", "B", "C", "D", "E", "F", "G")
        val exception =
            assertThrows<IllegalArgumentException> {
                BlackJack(names)
            }
        assertEquals("Maximum player names must be 6", exception.message)
    }

    @Test
    fun `setPlayersBet should call doRequest for each player`() {
        val game = BlackJack(names)

        game.setPlayersBet { BET }

        game.players.value.forEach {
            assertEquals(BET, it.bet)
        }
    }

    @Test
    fun `playersTurn should apply decision and doAfter to all players`() {
        val game = BlackJack(names)
        val called = mutableListOf<String>()

        game.playersTurn(
            doAfter = { called.add(it.name) },
            decision = dummyDecision(),
        )

        assertEquals(N * names.size, called.size)
        assertEquals(listOf("brie", "jason", "justin"), called)
    }

    @Test
    fun `dealerTurn should use decision and call doAfter`() {
        val game = BlackJack(names)

        var dealerTurnExecuted = true
        game.dealerTurn(
            deck = game.deck,
            doAfter = { dealerTurnExecuted = false },
            decision = { false },
        )

        assertEquals(true, dealerTurnExecuted)
    }

    @Test
    fun `calcEarning should update player and dealer earnings correctly`() {
        val game = BlackJack(listOf("brie"))
        val player = game.players.value.first()

        player.bet = BET
        player.earning = Earning(0)
        game.dealer.earning = Earning(0)

        player.drawCard(Card(Rank.EIGHT, Suite.CLUBS))
        player.drawCard(Card(Rank.EIGHT, Suite.CLUBS))

        game.dealer.drawCard(Card(Rank.KING, Suite.DIAMONDS))
        game.dealer.drawCard(Card(Rank.NINE, Suite.SPADES))

        game.calcEarning()

        assertEquals(-BET, player.earning.value)
        assertEquals(BET, game.dealer.earning.value)
    }

    companion object {
        val names = listOf("brie", "jason", "justin")
        const val BET = 1000
        const val N = 1

        fun dummyDecision(): (BasePlayer) -> Boolean {
            val counters = mutableMapOf<String, Int>()
            return { player: BasePlayer ->
                val remaining = counters.getOrDefault(player.name, N)
                if (remaining > 0) {
                    counters[player.name] = remaining - 1
                    true
                } else {
                    false
                }
            }
        }
    }
}
