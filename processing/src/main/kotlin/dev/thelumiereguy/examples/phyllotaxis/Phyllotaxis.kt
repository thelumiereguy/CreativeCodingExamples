package dev.thelumiereguy.examples.phyllotaxis

import processing.core.PApplet
import processing.event.KeyEvent

class Phyllotaxis : PApplet() {

    init {
        setSize(1024, 1024)
        runSketch()
    }

    override fun setup() {
        colorMode(HSB, 360f, 100f, 100f, 100f)
        frameRate(120f)
        noStroke()
        background(0)

    }

    override fun keyPressed(event: KeyEvent?) {
        super.keyPressed(event)
        if (event?.key == 's') {
            save("/birdsEye/BirdsEye.png")
        }
    }

    private val constant = 4

    override fun draw() {

        translate(width / 2f, height / 2f)

        val current = frameCount

        val angle = current * 137.5f
        val radius = constant * sqrt(current.toFloat())

        val x = radius * cos(angle)
        val y = radius * sin(angle)

        fill(
            map(radius % 100, 0f, 100f, 255f, 0f),
            radius % 75,
            radius % 100
        )

        circle(x, y, radius % 5)
    }
}
