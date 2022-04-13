package dev.thelumiereguy.util

import processing.core.PApplet
import processing.core.PVector


internal fun PApplet.plotShape(
    location: PVector,
    sides: Int,
    length: Float,
    rotationPhase: Float,
    drawAtVertexBlock: (VertexData) -> Unit = { _ -> }
) {

    pushMatrix()

    val rotationAngle = 360f / sides

    translate(
        location.x,
        location.y
    )

    rotate(PApplet.radians(rotationPhase))

    repeat(
        sides
    ) { side ->

        val startAngle = PApplet.radians(rotationAngle * side)
        val startX = PApplet.cos(startAngle) * length
        val startY = PApplet.sin(startAngle) * length

        val endAngle = PApplet.radians(rotationAngle * (side + 1))

        val endX = PApplet.cos(endAngle) * length
        val endY = PApplet.sin(endAngle) * length

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
