package dev.thelumiereguy.examples.divine_intervention

import processing.core.PApplet

class DivineIntervention : PApplet() {

    init {
        setSize(1024, 1024)
        runSketch()
    }

    override fun setup() {
        colorMode(HSB, 360f, 100f, 100f)
        background(0)
        strokeCap(ROUND)
        repeat(200) {
            stroke(255)
            line(width / 2f, -100f, random(width.toFloat()), random(height.toFloat()))
        }
    }


    private var scale = 1f
    private var rotation = 1f

    override fun draw() {


        translate(width / 2f, height / 2f)
        scale(scale)
        stroke(0)
        strokeWeight(1.5f)
        (1..30).forEach { rings ->
            rotation += 0.000005f
            fill(5f, rings * 2F, 90f)
            (1..rings).forEach {
                val radius = 8f * rings
                val angle = radians(((360f / rings) * it)) + (rotation + (500 / rings) * scale)
                val x = radius * cos(angle)
                val y = radius * sin(angle)
                circle(x, y, max(rings * 0.3f, 2f))
            }
        }

        scale += 0.0005f

//        saveFrame("/${this.javaClass.name}/#####.png")
    }
}
