package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerResultTrackerTest {
    private lateinit var tracker: DealerResultTracker

    @BeforeEach
    fun setUp() {
        tracker = DealerResultTracker()
    }

    @Test
    fun `should initialize all results with zero count`() {
        val output = tracker.toString()
        assertThat(output).contains("Win: 0")
        assertThat(output).contains("Lose: 0")
        assertThat(output).contains("Tie: 0")
    }

    @Test
    fun `should correctly increment result counts`() {
        tracker.record(Result.WIN)
        tracker.record(Result.LOSE)
        tracker.record(Result.TIE)
        tracker.record(Result.WIN)

        val output = tracker.toString()

        assertThat(output).contains("Win: 2")
        assertThat(output).contains("Lose: 1")
        assertThat(output).contains("Tie: 1")
    }

    @Test
    fun `toString should format result counts properly`() {
        tracker.record(Result.LOSE)
        tracker.record(Result.LOSE)

        val resultString = tracker.toString()
        assertThat(resultString).contains("Lose: 2")
        assertThat(resultString).contains("Win: 0")
        assertThat(resultString).contains("Tie: 0")
    }
}
