package dev.thelumiereguy.examples.fractal_spiralograph

import processing.core.PApplet
import processing.core.PApplet.*

class Orbit(
    private var xCord: Float,
    private var yCord: Float,
    private val radius: Float,
    private val iteration: Int = 0,
    private val parent: Orbit? = null,
    private val speed: Float = radians(pow(-9f, iteration - 1f)),
    private var angle: Float = 0f,
) {

    private var child: Orbit? = null

    init {
        if (iteration < 10) {
            val newRadius = radius / 3f
            val newX = xCord + radius + newRadius
            val newY = yCord
            child = Orbit(newX, newY, newRadius, iteration + 1, this)
        }
    }

    fun getCoords(): Pair<Float, Float> {
        return when {
            child != null -> child!!.getCoords()
            else -> xCord to yCord
        }
    }

    fun drawCircle(applet: PApplet) {
        if (parent != null)
            applet.circle(xCord, yCord, radius * 2f)
        child?.drawCircle(applet)
    }

    fun update() {
        if (parent != null) {
            angle += (speed) * 0.5f
            val radiusSum = radius + parent.radius
            xCord = parent.xCord + radiusSum * cos(angle)
            yCord = parent.yCord + radiusSum * sin(angle)
        }
        child?.update()
    }

}
