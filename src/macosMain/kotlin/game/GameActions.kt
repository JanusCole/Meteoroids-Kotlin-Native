package game

class GameActions () {

    val gameObjects : MutableList <GameObject> = mutableListOf()
    val player = Player()

    init {
        for (i in 1..20) {
            gameObjects.add(Meteor())
        }
        gameObjects.add(player)
        gameObjects.add(AlienShip())
    }

    fun updateGame() {
        gameObjects.forEach {
            it.updateLocation()
        }

        val newGameObjects : MutableList <GameObject> = mutableListOf()

        gameObjects.forEach {
            val collidingObject = it
            gameObjects.forEach {
                newGameObjects.addAll(it.checkForCollision(collidingObject))
            }
        }

        gameObjects.addAll(newGameObjects)

        gameObjects.retainAll {it.destroy == false}
    }

    fun processInput (inputChar : Int) {
        when (inputChar.toChar()) {
            'x' -> gameOver = true
            'n' -> player.moveUp()
            'm' -> player.moveDown()
            'a' -> gameObjects.add(Laser(player.getXLocation() + 3, player.getYLocation(), 1))
        }
    }

}