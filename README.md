# kotlin-blackjack

## Requirements

- [x] Each player must place a bet at the start of the game.
- [x] Card values follow standard Blackjack rules:
  - Number cards are counted by their face value.
  - Face cards (King, Queen, Jack) are each worth 10.
  - Aces can count as either 1 or 11.
  - Players are dealt two cards at the beginning of the game.
  - Players can choose to draw additional cards as long as their total does not exceed 21.
- [x] If a player draws a card and the total exceeds 21, they lose their entire bet.
- [x] If a player hits 21 with the initial two cards (Blackjack), they receive 1.5x their bet.
- [x] If both the player and dealer have Blackjack, the player’s bet is returned.
- [x] The dealer must draw an additional card if their initial total is 16 or less.
- [x] The dealer must stand on 17 or more.
- [x] If the dealer busts (exceeds 21), all remaining players automatically win and receive payouts based on their bets.

---

### approach, from review
- [x] empty deck() handling, avoid: `validating it's own size before a hit`
- [ ] modify Suit enum so that it doesn't interfere with symbol printing
- [x] CardHolder draw multi cards
- [x] CardHolder receive multi cards
