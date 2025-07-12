package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerResultTrackerTest {
    private lateinit var tracker: PlayerResultTracker

    @BeforeEach
    fun setUp() {
        tracker = PlayerResultTracker()
    }

    @Test
    fun `should return default message when no result is recorded`() {
        val output = tracker.toString()
        assertThat(output).isEqualTo("No result recorded.")
    }

    @Test
    fun `should return correct description after recording a result`() {
        tracker.record(Result.WIN)
        assertThat(tracker.toString()).isEqualTo("Win")

        tracker.record(Result.TIE)
        assertThat(tracker.toString()).isEqualTo("Tie")

        tracker.record(Result.LOSE)
        assertThat(tracker.toString()).isEqualTo("Lose")
    }
}
