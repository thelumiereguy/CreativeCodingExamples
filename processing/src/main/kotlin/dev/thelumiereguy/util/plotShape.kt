package dev.thelumiereguy.util

import processing.core.PApplet
import processing.core.PVector

/**
 * A makeshift algorithm to draw any shape with [sides] with [radius]
 * You can rotate the shape by specifying the [rotationPhase] param
 *
 * [drawAtVertexBlock] block can be used to draw something at each vertex of the original shape
 */
internal fun PApplet.plotShape(
    center: PVector,
    sides: Int,
    radius: Float,
    rotationPhase: Float,
    drawAtVertexBlock: (VertexData) -> Unit = { _ -> }
) {

    pushMatrix()

    val rotationAngle = 360f / sides

    translate(
        center.x,
        center.y
    )

    rotate(PApplet.radians(rotationPhase))

    repeat(
        sides
    ) { side ->

        val startAngle = PApplet.radians(rotationAngle * side)
        val startX = PApplet.cos(startAngle) * radius
        val startY = PApplet.sin(startAngle) * radius

        val endAngle = PApplet.radians(rotationAngle * (side + 1))

        val endX = PApplet.cos(endAngle) * radius
        val endY = PApplet.sin(endAngle) * radius

        drawAtVertexBlock(
            VertexData(
                startX to startY,
                endX to endY,
                side
            )
        )

        line(
            startX,
            startY,
            endX,
            endY
        )
    }

    popMatrix()
}


data class VertexData(
    val startPair: Pair<Float, Float>,
    val endPair: Pair<Float, Float>,
    val sideIndex: Int
)
