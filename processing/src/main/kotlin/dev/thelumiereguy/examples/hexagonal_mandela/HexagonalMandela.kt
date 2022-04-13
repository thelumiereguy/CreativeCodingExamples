package dev.thelumiereguy.examples.hexagonal_mandela

import dev.thelumiereguy.util.plotShape
import dev.thelumiereguy.util.rotate
import processing.core.PApplet
import processing.core.PVector

class HexagonalMandela : PApplet() {

    init {
        setSize(800, 800)
        runSketch()
    }

    override fun setup() = Unit

    private var upTime = 0

    override fun draw() {

        translate(
            width / 2f,
            height / 2f
        )

        background(0)

        //backdrop
        fill(0)
        noStroke()
        circle(
            0f, 0f, 300f
        )

        stroke(255f, 150f)

        strokeWeight(1f)

        val animatedRotationAngle = radians(sin(upTime.toFloat() * 0.001f) * 360f)

        noFill()

        pushMatrix()

        (0 until 360 step 10).forEach { angle ->
            rotate(radians(angle.toFloat() + (animatedRotationAngle * 4))) {
                ellipse(150f, 0f, 100f, 150f * animatedRotationAngle)
            }
        }
        popMatrix()

        fill(0)

        circle(
            0f,
            0f,
            300f
        )

        noFill()

        rotate(angle = animatedRotationAngle) {
            drawComplexShape()
        }

        var length = 150f

        var scale = 1.0f

        repeat(6) { iteration ->

            val animatedRotationAngleIteration = -radians(sin((upTime + iteration) * 0.001f) * 360f)

            scale /= 1.7f

            length *= (1.2f) * abs(sin(upTime * 0.001f) + 0.8f)

            val sides = 6

            val rotationPhase = 30f * iteration

            val alpha = 150f - (iteration * 15)

            rotate(angle = animatedRotationAngleIteration) {
                plotShape(
                    PVector(
                        0f,
                        0f
                    ),
                    sides,
                    length,
                    rotationPhase
                ) { vertexData ->

                    val startX = vertexData.startPair.first
                    val startY = vertexData.startPair.second

                    stroke(255f, alpha)
                    val angle = atan2(startY, startX)
                    pushMatrix()
                    translate(startX, startY)
                    rotate(angle + animatedRotationAngleIteration)
                    scale(scale)
                    drawComplexShape()
                    popMatrix()
                    stroke(0f, 0f)
                }
            }
        }

//        saveFrame("../art/${this::class.simpleName}/#####.png")

        upTime++
    }

    private fun drawComplexShape() {
        plotShape(
            PVector(
                0f,
                0f
            ),
            3,
            100f,
            90f
        )

        plotShape(
            PVector(
                0f,
                0f
            ),
            3,
            100f,
            270f
        )

        plotShape(
            PVector(
                0f,
                0f
            ),
            6,
            120f,
            0f
        )

        plotShape(
            PVector(
                0f,
                0f
            ),
            6,
            100f,
            90f
        ) { vertexData ->

            val startX = vertexData.startPair.first
            val startY = vertexData.startPair.second

            val endX = vertexData.startPair.first
            val endY = vertexData.startPair.second

            circle(
                vertexData.startPair.first,
                vertexData.startPair.second,
                40f
            )

            fill(128, 100f)

            circle(
                (startX + endX) / 2f,
                (startY + endY) / 2f,
                30f
            )

            circle(
                (startX + endX) / 2f,
                (startY + endY) / 2f,
                10f
            )

            noFill()
        }

        plotShape(
            PVector(
                0f,
                0f
            ),
            6,
            60f,
            90f
        )

        plotShape(
            PVector(
                0f,
                0f
            ),
            3,
            60f,
            90f
        ) { vertexData ->
            circle(
                vertexData.startPair.first,
                vertexData.startPair.second,
                40f
            )
        }

        plotShape(
            PVector(
                0f,
                0f
            ),
            3,
            60f,
            270f
        ) { vertexData ->
            circle(
                vertexData.startPair.first,
                vertexData.startPair.second,
                40f
            )
        }

        (0..360 step 90).forEach { angle ->
            rotate(radians(angle.toFloat())) {
                plotShape(
                    PVector(
                        0f,
                        0f
                    ),
                    3,
                    40f,
                    90f
                )
            }
        }
    }
}


