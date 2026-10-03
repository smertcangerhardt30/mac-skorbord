# 🏀 Basketball Scoreboard

A console-based basketball scoreboard written in Java. It records every shot and foul during a game and calculates player stats, quarter-by-quarter scores and the winner.

Built as an object-oriented programming practice project.

## Features

- Create two teams with up to 12 players each
- Record free throws, 2-pointers and 3-pointers (with distance) by quarter
- Track fouls — players foul out after 5
- Player stats: points, shooting percentage, made 3-pointers
- Live scoreboard with quarter breakdown and winner
- Input validation everywhere — the app never crashes on bad input

## OOP Concepts Used

| Concept | Where |
|---|---|
| Encapsulation | All fields are private, validated through constructors and methods |
| Abstract classes | `Shot` defines shared behavior, subclasses define the point value |
| Inheritance | `TwoPointShot`, `ThreePointShot`, `FreeThrow` extend `Shot` |
| Polymorphism | `getPoints()` works on any shot type without type checks |
| Interfaces | `Scoreable` is implemented by both `Player` and `Team` |
| Composition | `Game` → `Team` → `Player` → `Shot` |
| Static members | Global shot counter, `MAX_FOULS`, `MAX_PLAYERS` constants |
| Exceptions | Model classes enforce rules, the app catches and reports them |

## Class Structure

```
Shot (abstract)              Scoreable (interface)
 ├── TwoPointShot              ├── Player
 ├── ThreePointShot            └── Team
 └── FreeThrow

Game ──has──> 2 × Team ──has──> Player list ──has──> Shot list
```

## How to Run

Requires JDK 17 or newer. From the project root:

```bash
javac -d out src/*.java
java -cp out src.ScoreboardApp
```

To run the fixed test scenario instead:

```bash
java -cp out src.Main
```

## Example

```
===== SCOREBOARD =====
1. Add shot
2. Add foul
3. Player stats
4. Show scoreboard
0. Finish game
Choice: 1
1 = Ankara Eagles, 2 = Izmir Seagulls
Select team: 1
Jersey number: 5
Shot type (1 = FT, 2 = 2PT, 3 = 3PT): 3
Quarter (1-4): 1
Made? (y/n): y
Distance (m): 7.5
Shot added: Q1 | 3PT | Made | 7.5m
```

```
Ankara Eagles 18 - 13 Izmir Seagulls
Quarters: 7-3 | 3-5 | 4-2 | 4-3
Winner: Ankara Eagles
```