package dev.thelumiereguy.examples.squiggly_wiggly

import processing.core.PApplet

class SquigglyWiggly : PApplet() {

    init {
        setSize(800, 600)
        runSketch()
    }


    override fun setup() {
        background(0)
    }

    private val maxTrailsSize = 300


    private val circleList = mutableListOf<() -> Unit>()

    private var noiseOffset = 0f

    private var radius = 0f

    override fun draw() {

        circleList.forEachIndexed { index, function ->
            if (index < maxTrailsSize) {
                fill(255f, minOf(index * 3f, 255f))
            }

            radius = (index.toFloat() % 30) + 1

            function.invoke()

            noiseOffset += 0.0001f
        }

        val offset = noiseOffset

        circleList.add {
            circle(
                noise(offset) * width,
                noise(offset, -offset) * height,
                radius
            )
        }

        if (circleList.size > maxTrailsSize) {
            circleList.removeFirstOrNull()
        }
    }
}
