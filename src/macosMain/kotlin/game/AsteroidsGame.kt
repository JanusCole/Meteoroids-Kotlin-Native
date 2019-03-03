package game
import platform.osx.*
import kotlinx.cinterop.*

val GameHeight = 30
val GameWidth = 100

var gameOver : Boolean = false
var score : Int = 0

lateinit var GameWindow: CPointer<WINDOW>

fun main (args : Array<String>) {
    playGame()
}

fun playGame()  = memScoped {

    var inputChar: Int = 0
    var gameActions = GameActions()

    // INITIALIZE THE SCREEN
    initscr()
    GameWindow = newwin(GameHeight, GameWidth, 0, 0)!!

    // SET UP INPUT WITH NO ECHO, CURSOR LOCATION AND DELAY
    noecho()
    curs_set(0)
    halfdelay(1)

    while (!gameOver) {

        // CLEAR WINDOW AND DRAW THE BORDER
        wclear(GameWindow)
        box(GameWindow, 0, 0)

        gameActions.processInput(inputChar)
        gameActions.updateGame()

        // DISPLAY THE WINDOW
        wrefresh(GameWindow)

        // GET INPUT WITH NON-BLOCKING HALFDELAY OPTION
        inputChar = wgetch(GameWindow)
    }

    // PAUSE THE GAME FOR 2 SECONDS TO DISPLAY SCORE
    halfdelay(20)
    wgetch(GameWindow)

    // CLEAN UP UI ELEMENTS TO PREVENT MEMORY LEAKS
    delwin(GameWindow)
    endwin()
}


