package dev.thelumiereguy.examples.circle_pattern

import processing.core.PApplet

class CirclePattern : PApplet() {

    init {
        setSize(1024, 1024)
        runSketch()
    }


    override fun setup() {
        stroke(0xfff)
        colorMode(HSB, 360f, 100f, 100f)
    }


    override fun draw() {
        background(20f)

        translate(width / 2f, height / 2f)

        ((1..400) step 30).forEach { radius ->
            val circumference = (2 * PI * radius)
            val circles = floor(circumference / 30)
            val steps = 360 / max(circles.toFloat(), 0.1f)

            var angle = 0f

            while (angle <= 360) {
                pushMatrix()
                rotate(radians(angle))

                val extent = (sin(radius + frameCount * 0.05f) + 1) * max(radius, 200) * 0.03f

                fill(
                    angle % 40,
                    (noise(angle + frameCount * 0.01f)) * 150f,
                    255f
                )
                circle(radius.toFloat(), 0f, extent)
                popMatrix()

                angle += steps
            }
        }

//        saveFrame("/${this.javaClass.name}/#####.png")
    }

}
