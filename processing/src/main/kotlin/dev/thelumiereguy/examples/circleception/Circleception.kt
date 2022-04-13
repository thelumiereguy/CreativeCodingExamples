package dev.thelumiereguy.examples.circleception

import dev.thelumiereguy.util.plotShape
import processing.core.PApplet
import processing.core.PVector

class Circleception : PApplet() {

    init {
        setSize(750, 1334)
        runSketch()
    }

    override fun setup() = Unit

    private var upTime = 0


    override fun draw() {

        colorMode(HSB, 360f, 1f, 1f)

        background(0)

        translate(
            width / 2f,
            height / 2f
        )

        val animatedAngle = sin(upTime * 0.001f) * 360f
        val animatedAngleInnerShape = cos(upTime * 0.005f) * 360f
        val animatedAngleSecondInnerShape = cos(upTime * 0.0001f) * 360f

        noFill()

        blendMode(ADD)

        plotShape(
            PVector(0f, 0f),
            6,
            150f,
            animatedAngle
        ) { shapeVertex ->

            stroke(0f, 0.7f, 0.5f)
            strokeWeight(5f)

            circle(
                shapeVertex.startPair.first,
                shapeVertex.startPair.second,
                300f
            )

            noStroke()

            plotShape(
                PVector(
                    shapeVertex.startPair.first,
                    shapeVertex.startPair.second
                ),
                3,
                75f,
                (if (shapeVertex.sideIndex % 2 == 0) 0f else 180f) + animatedAngleInnerShape
            ) { innerShapeVertex ->

                stroke(20f, 0.5f, 0.5f)

                strokeWeight(3f)

                circle(
                    innerShapeVertex.startPair.first,
                    innerShapeVertex.startPair.second,
                    150f
                )

                noStroke()

                plotShape(
                    PVector(
                        innerShapeVertex.startPair.first,
                        innerShapeVertex.startPair.second,
                    ),
                    3,
                    37f,
                    (if (innerShapeVertex.sideIndex % 2 == 0) 00f else 90f) + animatedAngleSecondInnerShape
                ) { secondInnerShapeVertex ->

                    stroke(20f, 0.3f, 0.3f)

                    strokeWeight(3f)

                    line(
                        secondInnerShapeVertex.startPair.first,
                        secondInnerShapeVertex.startPair.second,
                        -secondInnerShapeVertex.startPair.first,
                        -secondInnerShapeVertex.startPair.second,
                    )

                    circle(
                        secondInnerShapeVertex.startPair.first,
                        secondInnerShapeVertex.startPair.second,
                        75f
                    )

                    noStroke()

                }
            }
        }

//        saveFrame("../art/${this::class.simpleName}/#####.png")

        upTime++
    }
}