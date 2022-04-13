package dev.thelumiereguy.examples.whirlpool

import processing.core.PApplet

class Whirlpool : PApplet() {

    init {
        setSize(800, 800)
        runSketch()
    }

    override fun setup() {
        colorMode(HSB, 360f, 100f, 100f)
        background(0)
    }


    private var scale = 1f
    private var rotation = 1f

    override fun draw() {

        translate(width / 2f, height / 2f)

        stroke(0)
        strokeWeight(1f)
        (1..30).forEach { rings ->
            rotation += 0.00005f
            fill(52f, rings * 2.5F, 100f)
            (1..rings).forEach {
                val radius = 10f * rings
                val angle = radians(((360f / rings) * it)) + (QUARTER_PI * (it * sin(rotation)))
                val x = radius * cos(angle)
                val y = radius * sin(angle)
                circle(x, y, max(rings * 0.3f, 2f))
            }
        }

        scale += 0.01f

//        saveFrame("/circleArray/#####.png")
    }
}
