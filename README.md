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

---

## Refactor, step 1

### Participant package
```
Participant -> Player & Dealer -> Participants
```

**Think about this/check later**
- [ ] Currently Participant has a rule that "all `Participant`s" have a "`name`", which is why it was changed to abstract class. Is this decision really necessary?
- [ ] Delete/move methods for tests

#### Participant
- [ ] Implement interface/abstract class `Participant` that can separate Player and Dealer
- [ ] Implement Parent class as `Participant` interface first, then change if needed
- 
#### Player & Dealer
- [ ] Implement `Player`
  - [ ] has `name`
- [ ] Implement `Dealer`
  - [ ] has `name` as "Dealer"

#### Participants
- [ ] Implement Participants that has List<Participant>
  - [ ] Contains Dealer
  - [ ] Contains Player
  - [ ] Implement contain/containsAll that can be used as test functions

### Holder package


