## Part 2 Plan
- Clean the animation controller/Main to follow clean code principles
- Move the brick layout functionality to its own class
- Manage collisions potentially through a "Collidable" interface
- Move the player movement management to the player controller
- Clean up paddle movement (remove jitter)
- Create a power up Brick

```mermaid
---
title: Breakout UML Plan
---
classDiagram
    class GameController{
        -playerController: PlayerController 
        -highScoreController: HighScoreController 
        -brickLayout: BrickLayout 
        -balls: List~Ball~
        -animationController:  AnimationController 
        -uiController: UI
        -gameLost: bool
        
        +run()
        -checkPlayerLives()
        -nextLevel()
        
    }

    
    class Collidable {
        <<interface>>
        -inCollision : bool
        +isCollidedWith() bool
    }

    
    class BrickLayout {
        - List~Brick~ bricks
        - startHealth: int
        - layerIncrement: int
        - randomPowerBrickChance: int
        - randomBlockerBrickChance: int

        +BrickLayout(totalBricks, startHealth, layerIncrement)
        +createBrickLayout()
        +removeBrick()
        +isBricksLeft() bool
        -colorCreator()
        -randomBrick()
    }
    BrickLayout<--Brick
    

    class Ball
    Ball<--Collidable

    class Block
    Block<--Collidable

    class Paddle
    Paddle<--Block

    class Brick
    Brick<--Block

    class PowerBrick
    PowerBrick<--Brick

    class Boundary
    Boundary<--Block
```

```mermaid
classDiagram
    class HighScoreController
    class PlayerController
    class AnimationController{
        +step()
    }

    note for UI "potential way to use an Event to update text"
    class UI{
        + UI(scene)
        + addElementView(view)
        + addElementText(element)
        + updateText()
    }
```
