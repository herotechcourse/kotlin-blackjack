package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EarningTest {
    @Test
    fun `calc should return Earning with correct value`() {
        val earning = Earning(0)
        val result = earning.calc(1000, 1.5)
        assertEquals(1500, result.value)
    }

    @Test
    fun `plus operator should sum values of two Earnings`() {
        val a = Earning(40000)
        val b = Earning(60000)
        val result = a + b
        assertEquals(100000, result.value)
    }
}
