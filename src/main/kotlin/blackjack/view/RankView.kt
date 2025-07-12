package blackjack.view

import blackjack.model.Rank

enum class RankView(val rank: Rank, val face: String) {
    TWO(Rank.TWO, "2"),
    THREE(Rank.THREE, "3"),
    FOUR(Rank.FOUR, "4"),
    FIVE(Rank.FIVE, "5"),
    SIX(Rank.SIX, "6"),
    SEVEN(Rank.SEVEN, "7"),
    EIGHT(Rank.EIGHT, "8"),
    NINE(Rank.NINE, "9"),
    TEN(Rank.TEN, "10"),
    JACK(Rank.JACK, "J"),
    QUEEN(Rank.QUEEN, "Q"),
    KING(Rank.KING, "K"),
    ACE(Rank.ACE, "A");

    companion object {
        fun fromRank(rank: Rank) : RankView =  RankView.entries.first { it.rank == rank }
    }
}
