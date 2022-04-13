package dev.thelumiereguy.examples.iris

import processing.core.PApplet
import kotlin.math.roundToInt

class Iris : PApplet() {

    init {
        setSize(1024, 1024)
        runSketch()
    }

    override fun setup() {
        smooth()
        background(30f)
        colorMode(HSB, 360f, 100f, 100f, 100f)
    }

    override fun draw() {
        val frameCount = frameCount.toFloat()
        background(0)
        translate(width / 2f, height / 2f)
        scale(noise(frameCount * 0.0009f) + 0.6f)
        rotate(-frameCount * 0.006f)

        (1..5).forEach {
            fill(0f, 0f, 100f, 100f / it)
            circle(0f, 0f, sin(frameCount * 0.01f) + (2f * it))
        }

        stroke(255)
        noFill()

        (1..(12)).forEach { ringIndex ->
            drawCircle(ringIndex)
        }

//        saveFrame("/curvedRings/#####.png")
    }

    private fun drawCircle(ringIndex: Int) {
        stroke(30f, min(frameCount * 0.5f, 60f), (10f / ringIndex) * 20)
        val maxSegments = min(frameCount * 0.6f, 150f).roundToInt()
        (0..maxSegments).forEach {
            if (it % 3 == 0) {
                val point = getPointCoords(maxSegments, it, ringIndex)
                beginShape()
                vertex(point.first, point.second)
                val nextPoint = getPointCoords(maxSegments, it + 1, ringIndex)
                val nextPoint2 = getPointCoords(maxSegments, it + 2, ringIndex)
                val nextPoint3 = getPointCoords(maxSegments, it + 3, ringIndex)
                bezierVertex(
                    nextPoint.first, nextPoint.second,
                    nextPoint2.first, nextPoint2.second,
                    nextPoint3.first, nextPoint3.second,
                )
                endShape()
            }
        }
    }

    private fun getPointCoords(maxSegments: Int, currentIndex: Int, ringIndex: Int): Pair<Float, Float> {
        val angle = radians((360f / maxSegments) * currentIndex)
        val x =
            (cos(angle) * ((ringIndex * 50) + (cos(currentIndex + angle + frameCount * 0.02f) * 10)))
        val y =
            (sin(angle) * ((ringIndex * 50) + (sin(currentIndex + angle - frameCount * 0.02f) * 10)))

        return x to y
    }

}
