package service

data class PlayerEarningResult(
    val playerId: Int,
    val earningsChange: Double,
    val dealerEarningChange: Double,
)
