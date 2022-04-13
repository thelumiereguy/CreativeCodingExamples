package dev.thelumiereguy.examples.fractal_spiralograph

import processing.core.PApplet
import processing.core.PVector

class FractalSpirograph : PApplet() {

    init {
        setSize(800, 800)
        runSketch()
    }


    override fun setup() {

    }

    private val list = mutableListOf<PVector>()


    private val orbit by lazy { Orbit(0f, 0f, 150f) }

    override fun draw() {
        background(0)
        stroke(255f)
        noFill()
        strokeWeight(2f)

        val x = width / 2f
        val y = height / 2f

        translate(x, y)

        orbit.run {
            update()
            drawCircle(this@FractalSpirograph)
            val newCoords = getCoords()
            list.add(PVector(newCoords.first, newCoords.second))
        }

        beginShape()
        list.forEachIndexed { index, pVector ->
            stroke(max(index / 3, 150))
            vertex(pVector.x, pVector.y)
        }
        endShape()

        if (list.size > 700) {
            list.removeFirstOrNull()
        }

//        noLoop()
    }

}
