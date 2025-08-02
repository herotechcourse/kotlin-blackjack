package model

class Earning(var value: Int) {
    fun calc(
        bet: Int,
        ratio: Double,
    ): Earning {
        return Earning((bet * ratio).toInt())
    }

    operator fun plus(other: Earning): Earning {
        return Earning(this.value + other.value)
    }

    override fun toString(): String {
        return value.toString()
    }
}
