package game

import platform.osx.mvwprintw
import kotlin.random.Random

class Meteor : GameObject () {

    init {
        location = GameObjectLocation(Random.nextInt(GameWidth, (GameWidth * 2)),
                                      Random.nextInt(5, GameHeight - 5))
        velocity = GameObjectVelocity(-1,0)
        size = GameObjectSize(1,0)
    }

    override fun updateLocation() {
        super.updateLocation()

        if (location.xLocation < 0) {
            resetLocation()
        }

        if (location.xLocation > GameWidth) {
            velocity.xVelocity = -1
        }

        mvwprintw(GameWindow, location.yLocation, location.xLocation, "*")
    }

    fun resetLocation() {
        location.xLocation = Random.nextInt(GameWidth + 5, (GameWidth * 2))
    }

    override fun checkForCollision (gameObject : GameObject) : MutableList <GameObject> {
        var newGameObjects : MutableList<GameObject> = mutableListOf()

        if (isColliding(gameObject)) {
            if (gameObject is Laser) {
                newGameObjects.add(LittleMeteor(getXLocation(), getYLocation(), -1))
                newGameObjects.add(LittleMeteor(getXLocation(), getYLocation(), 1))
                newGameObjects.add(Explosion(getXLocation(), getYLocation()))
                newGameObjects.add(Meteor())
                gameObject.destroy = true
                this.destroy = true
                score++
            }

            if (gameObject is AlienShip) {
                newGameObjects.add(Explosion(getXLocation(), getYLocation()))
                newGameObjects.add(Meteor())
                this.destroy=true
            }
        }

        return newGameObjects
    }

}
