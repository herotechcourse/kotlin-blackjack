package blackjack.model

import blackjack.enum.CardSuit
import blackjack.state.Blackjack
import blackjack.state.Busted
import blackjack.state.Finished
import blackjack.state.Running
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantResultTest {
    private lateinit var participant: Participant
    private lateinit var dealer: Participant
    private lateinit var result: ParticipantResult

    @BeforeEach
    fun setUp() {
        result = ParticipantResult()
        participant = Player("Player", Deck(mutableListOf()))
        dealer = Dealer(Deck(mutableListOf()))
    }

    @Test
    fun `returns -1_0 if player is busted`() {
        participant.state =
            Busted(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 10),
                        Card(CardSuit.CLUB, 5),
                    ),
                ),
                Deck(mutableListOf()),
            )
        dealer.state =
            Running(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 7),
                    ),
                ),
                Deck(mutableListOf()),
            )
        assertEquals(-1.0, result.calculateEarningsRate(participant, dealer))
    }

    @Test
    fun `returns 1_5 if dealer is busted and player is blackjack`() {
        participant.state =
            Blackjack(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 1),
                        Card(CardSuit.SPADE, 10),
                    ),
                ),
                Deck(mutableListOf()),
            )
        dealer.state =
            Busted(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 10),
                        Card(CardSuit.CLUB, 5),
                    ),
                ),
                Deck(mutableListOf()),
            )
        assertEquals(1.5, result.calculateEarningsRate(participant, dealer))
    }

    @Test
    fun `returns 1_0 if dealer is busted and player is not blackjack`() {
        participant.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 7),
                    ),
                ),
                Deck(mutableListOf()),
            )
        dealer.state =
            Busted(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 10),
                        Card(CardSuit.CLUB, 5),
                    ),
                ),
                Deck(mutableListOf()),
            )
        assertEquals(1.0, result.calculateEarningsRate(participant, dealer))
    }

    @Test
    fun `returns 1_5 if player has more points and is blackjack`() {
        participant.state =
            Blackjack(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 1),
                        Card(CardSuit.SPADE, 10),
                    ),
                ),
                Deck(mutableListOf()),
            )
        dealer.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 7),
                    ),
                ),
                Deck(mutableListOf()),
            )
        assertEquals(1.5, result.calculateEarningsRate(participant, dealer))
    }

    @Test
    fun `returns 1_0 if player has more points and is not blackjack`() {
        participant.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 8),
                    ),
                ),
                Deck(mutableListOf()),
            )
        dealer.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 7),
                    ),
                ),
                Deck(mutableListOf()),
            )
        assertEquals(1.0, result.calculateEarningsRate(participant, dealer))
    }

    @Test
    fun `returns 0_0 if player and dealer have same points`() {
        participant.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 7),
                    ),
                ),
                Deck(mutableListOf()),
            )
        dealer.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 7),
                    ),
                ),
                Deck(mutableListOf()),
            )
        assertEquals(0.0, result.calculateEarningsRate(participant, dealer))
    }

    @Test
    fun `returns -1_0 if player has fewer points`() {
        participant.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 7),
                    ),
                ),
                Deck(mutableListOf()),
            )
        dealer.state =
            Finished(
                Hand(
                    mutableListOf(
                        Card(CardSuit.HEART, 10),
                        Card(CardSuit.SPADE, 8),
                    ),
                ),
                Deck(mutableListOf()),
            )
        assertEquals(-1.0, result.calculateEarningsRate(participant, dealer))
    }
}
