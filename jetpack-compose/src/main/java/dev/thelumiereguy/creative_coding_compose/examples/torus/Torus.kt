package dev.thelumiereguy.creative_coding_compose.examples.torus

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import dev.romainguy.kotlin.math.Float2
import dev.romainguy.kotlin.math.Mat2
import dev.romainguy.kotlin.math.radians
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun Torus(modifier: Modifier) {

    BoxWithConstraints(
        modifier = modifier.background(
            Color.Black
        )
    ) {

        val screenHeight = constraints.maxHeight.toFloat()
        val screenWidth = constraints.maxWidth.toFloat()

        /**
         * Infinite animation that linearly interpolates from 10 to 0f
         * within 15 seconds and reverse
         */
        val animationFrame by rememberInfiniteTransition().animateFloat(
            initialValue = 10f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                tween(
                    15000,
                    easing = LinearEasing
                ),
                RepeatMode.Reverse
            ),
        )

        // Minor Radius
        val innerRadius = remember {
            (screenWidth / screenHeight) * screenWidth * 0.3f
        }

        // Major radius
        val outerRadius = 50 * animationFrame

        val rotationMatrix = Mat2(
            Float2(sin(animationFrame), 0f),
            Float2(0f, cos(animationFrame))
        )

        Canvas(modifier = modifier) {
            // Translate to center of screen
            translate(
                screenWidth / 2,
                screenHeight / 2
            ) {

                // Loop for drawing 360 circles
                (0 until 360).forEach { angle ->

                    val angleF = angle.toFloat()

                    val angleRadians = radians(angleF)

                    val rotatedCenter = rotationMatrix * Float2(
                        cos(angleRadians) * outerRadius,
                        sin(angleRadians) * outerRadius
                    )


                    drawCircle(
                        Color.hsl(
                            angleF,
                            0.6f,
                            0.5f,
                            (abs(rotatedCenter.x) / screenWidth) + 0.1f
                        ),
                        radius = innerRadius,
                        center = Offset(
                            rotatedCenter.x,
                            rotatedCenter.y
                        ),
                        style = Stroke(
                            width = 10f,
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(10f, 10f),
                            )
                        ),
                        blendMode = BlendMode.Lighten
                    )
                }
            }
        }
    }
}