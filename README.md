# Kotlin-Blackjack

## Description:
A simplified terminal-based Blackjack game written in Kotlin.
The player or dealer with a total closest to 21—without going over—wins.
Includes card dealing, drawing logic, and win/loss/draw result handling.

## Features: 
### Players:
- [ ] Create Players abstract class with hand management
- [ ] Implement Card class with display and score logic

### Dealer
- [ ] Deal card to any participant
- [ ] Dealer auto-draws when score ≤ 16
- [ ] Ask players if they want to draw more cards
- [ ] Check if a participant is busted
- [ ] Calculate participant score with Ace logic
- [ ] Announce game results

### Game
- [ ] Accept player names
- [ ] Deal two initial cards to all participants
- [ ] Handle player turns
- [ ] Handle dealer turn
- [ ] Display final hands and scores
- [ ] Determine and print winners using GameLogic.getGameResult()