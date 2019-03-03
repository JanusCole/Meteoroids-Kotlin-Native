package game

import platform.osx.mvwprintw

class LittleMeteor (val xStartLocation : Int, val yStartLocation : Int, val yVelocity : Int) : GameObject () {

    init {
        location = GameObjectLocation(xStartLocation,yStartLocation)
        velocity = GameObjectVelocity(-2,yVelocity)
        size = GameObjectSize(0,0)
    }

    override fun updateLocation() {
        super.updateLocation()
        mvwprintw(GameWindow, location.yLocation, location.xLocation, "*")

        if (getXLocation() < 0) {
            this.destroy = true
        }
    }

    fun changeDirection() {
        velocity.yVelocity *= -1
    }

    override fun checkForCollision (gameObject : GameObject) : MutableList <GameObject> {
        var newGameObjects : MutableList<GameObject> = mutableListOf()
        if (isColliding(gameObject)) {
            if (gameObject is Laser) {
                gameObject.destroy = true
                this.destroy = true
                newGameObjects.add(Explosion(getXLocation(), getYLocation()))
                score++
            }

            if (gameObject is AlienShip) {
                this.destroy = true
                newGameObjects.add(Explosion(getXLocation(), getYLocation()))
            }

            if ((gameObject is Meteor) || (gameObject is LittleMeteor)) {
                changeDirection()
            }
        }

        return newGameObjects
    }

}
