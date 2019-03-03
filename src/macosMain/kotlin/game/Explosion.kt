package game

import platform.osx.mvwprintw

class Explosion (val xStartLocation : Int, val yStartLocation : Int) : GameObject () {

    init {
        location = GameObjectLocation(xStartLocation,yStartLocation)
        velocity = GameObjectVelocity(0,0)
        size = GameObjectSize(1,0)
    }

    override fun updateLocation() {
        super.updateLocation()
        mvwprintw(GameWindow, getYLocation() - 1, getXLocation() - 1, "\\ /")
        mvwprintw(GameWindow, getYLocation(), getXLocation(), "X")
        mvwprintw(GameWindow, getYLocation() + 1, getXLocation() - 1, "/ \\")

        this.destroy = true
    }
}
