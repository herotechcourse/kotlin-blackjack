package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PlayerWalletTest {
    @Test
    fun `should place a valid bet`() {
        val wallet = PlayerWallet()
        wallet.placeBet(100)
        assertThat(wallet.bet).isEqualTo(100)
    }

    @Test
    fun `should not allow placing a zero or negative bet`() {
        val wallet = PlayerWallet()

        assertThatThrownBy { wallet.placeBet(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Bet must be a positive number.")

        assertThatThrownBy { wallet.placeBet(-50) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Bet must be a positive number.")
    }
}
