package game

import platform.osx.mvwprintw

class Player : GameObject () {

    init {
        location = GameObjectLocation(3,GameHeight / 2)
        velocity = GameObjectVelocity(0,0)
        size = GameObjectSize(2,1)
    }

    override fun updateLocation() {
        super.updateLocation()

        velocity.yVelocity = 0

        if (location.yLocation < 1) {
            location.yLocation = 1
        }

        if (location.yLocation > GameHeight - 1) {
            location.yLocation = GameHeight - 1
        }

        mvwprintw(GameWindow, location.yLocation, location.xLocation, "[>>")
    }

    fun moveUp() {
        velocity.yVelocity = -1
    }

    fun moveDown() {
        velocity.yVelocity = 1
    }

    override fun checkForCollision (gameObject : GameObject) : MutableList <GameObject> {

        if (isColliding(gameObject)) {
            gameOver = true
        }

        return mutableListOf()
    }
}
