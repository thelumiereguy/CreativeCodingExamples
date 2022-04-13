package dev.thelumiereguy.examples.arc_waves

import processing.core.PApplet

class ArcWaves : PApplet() {

    private var perlinOffset = 0f

    init {
        setSize(852, 852)
        runSketch()
    }


    override fun setup() {

    }


    override fun draw() {

        translate(width / 2f, height / 2f)

        background(37f, 74f, 108f)

        noFill()

        rotate(perlinOffset * 0.1f)

        perlinOffset += 0.05f

        val ringGapWidth = 35

        (0..width / 2 step ringGapWidth).mapIndexed { index, horizontalPixel ->

            stroke(135f, 164f, 196f, 255f)


            val gapsCount = fibonacci(index + 5).first()

            val angleGap = radians((360f / gapsCount))

            strokeWeight(perlinOffset / gapsCount)

            repeat(gapsCount.toInt()) {

                rotate(angleGap)

                val angle = atan2(
                    0f,
                    horizontalPixel.toFloat()
                )

                arc(
                    horizontalPixel.toFloat(),
                    0f,
                    25f,
                    25f,
                    sin(horizontalPixel.toFloat()) * angle,
                    angle + PI
                )
            }
        }
    }

    private fun fibonacci(n: Int) = sequence {
        var previousResult: Long = 0
        var result: Long = 1

        repeat(n) {
            previousResult = result.also {
                result += previousResult
            }
        }

        yield(previousResult)
    }
}
