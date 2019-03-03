package game

import platform.osx.mvwprintw
import kotlin.random.Random

class AlienShip : GameObject () {

    init {
        location = GameObjectLocation(Random.nextInt(25, GameWidth - 5),
                                      Random.nextInt(-10, -5))
        velocity = GameObjectVelocity(0,1)
        size = GameObjectSize(1,1)
    }

    override fun updateLocation() {
        super.updateLocation()

        if (location.yLocation > GameHeight - 5) {
            velocity.yVelocity = -1
        }

        if (location.yLocation < -10) {
            velocity.yVelocity = 1
            location.xLocation = Random.nextInt(25, GameWidth - 5)
        }

        mvwprintw(GameWindow, location.yLocation, location.xLocation, "/\\")
        mvwprintw(GameWindow, location.yLocation + 1, location.xLocation - 1, "<__>")
    }

    override fun checkForCollision (gameObject : GameObject) : MutableList <GameObject> {
        var newGameObjects : MutableList<GameObject> = mutableListOf()
        if (isColliding(gameObject)) {
            if (gameObject is Laser) {
                gameObject.destroy = true
                newGameObjects.add(Explosion(getXLocation(), getYLocation()))
                location = GameObjectLocation(Random.nextInt(25, GameWidth - 5),
                    Random.nextInt(-10, -5))
                score++
            }
        }

        if (Random.nextInt(1, 2000) == 5) {
            newGameObjects.add(Laser(getXLocation() - 2, getYLocation(), -1))
            newGameObjects.add(Laser(getXLocation() - 4, getYLocation(), -1))
            newGameObjects.add(Laser(getXLocation() - 6, getYLocation(), -1))
        }

        return newGameObjects
    }
}
