package game

import platform.osx.mvwprintw

class Laser (val xStartLocation : Int, val yStartLocation : Int, val xVelocity : Int) : GameObject () {

    init {
        location = GameObjectLocation(xStartLocation,yStartLocation)
        velocity = GameObjectVelocity(xVelocity,0)
        size = GameObjectSize(1,0)
    }

    override fun updateLocation() {
        super.updateLocation()
        mvwprintw(GameWindow, location.yLocation, location.xLocation, "-")

        if ((getXLocation() < 0) || (getXLocation() > GameWidth)) {
            this.destroy = true
        }
    }
}
