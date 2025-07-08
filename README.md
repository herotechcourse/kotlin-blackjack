# kotlin-blackjack

## Requirements

- Card values follow standard Blackjack rules:
- Number cards are counted by their face value.
- Face cards (King, Queen, Jack) are each worth 10.
- Aces can be worth either 1 or 11.
- Each player starts with two cards.
- Players may draw additional cards as long as their total remains 21 or less.
- The dealer must draw a card if their total is 16 or less, and must stand on 17 or more.
- If the dealer busts (goes over 21), all remaining players automatically win.
- After the game ends, display the result (win/loss) for each player.
- 
## Features
- [ ] Validate input name of players
- [ ]

## Features

### Card : data class
- [x] implement simple card class
- [ ] value: 1 to 11
- [ ] Face cards (King, Queen, Jack) are each worth 10
- [ ] type: Ace, , Number
- [ ] symbol: enum class { hearts, spades , clubs, diamonds }
  - [ ] toString(): return symbol character
- 
### Type enum
- [ ] isAce = false -> true  -> eval value to 11

### Cards : data class  -> Hand, CardDeck  
- [ ] mutable <data structure>
- [ ] HashSet<Card>
- [ ] .size
- 
### todo
- [ ] implement 
