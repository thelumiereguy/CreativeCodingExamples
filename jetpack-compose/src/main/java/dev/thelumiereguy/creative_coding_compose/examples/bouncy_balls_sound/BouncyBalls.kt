package dev.thelumiereguy.creative_coding_compose.examples.bouncy_balls_sound

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.translate
import dev.romainguy.kotlin.math.radians
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private const val maxIterations = 6

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun BouncyBalls(modifier: Modifier) {
    BoxWithConstraints(
        Modifier
            .background(Color.Black)
    ) {

        val infiniteTransition = rememberInfiniteTransition()

        val startAngle = remember {
            radians(-45f)
        }

        val endAngle = remember {
            radians(-135f)
        }

        val toneManager = remember {
            ToneGenerator(
                AudioManager.STREAM_MUSIC, 100
            )
        }

        val handler = remember {
            Handler(Looper.getMainLooper())
        }

        val sideCount = (0..maxIterations).map {
            infiniteTransition.animateFloat(
                initialValue = startAngle,
                targetValue = endAngle,
                animationSpec = infiniteRepeatable(
                    tween(
                        1000 + (it * 100),
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }

        val leftBound = remember {
            Offset(
                x = getXCoord(endAngle - 0.05f, maxIterations + 1),
                y = getYCoord(endAngle - 0.05f, maxIterations + 1),
            )
        }

        val rightBound = remember {
            Offset(
                x = getXCoord(startAngle + 0.05f, maxIterations + 1),
                y = getYCoord(startAngle + 0.05f, maxIterations + 1),
            )
        }


        Canvas(
            modifier = Modifier.then(
                modifier
            ),
        ) {
            translate(
                constraints.maxWidth / 2f,
                constraints.maxHeight / 1.5f,
            ) {

                var previousPos: Offset? = null

                repeat(maxIterations) {

                    val index = it + 1

                    val angle = sideCount[it].value

                    val x = getXCoord(angle, index)
                    val y = getYCoord(angle, index)


                    /**
                     * Plays a tone
                     * toneType is randomized between 0 to 24 (constants from [ToneGenerator])
                     */
                    if (angle > startAngle - 0.01f) {
                        handler.post {
                            toneManager.startTone(
                                Random.nextInt(0, 24),
                                100
                            )
                        }
                    }

                    val currentPos = Offset(x, y)

                    previousPos?.let { previousPos ->
                        drawLine(
                            Color.White,
                            start = previousPos,
                            end = currentPos,
                            strokeWidth = 1f,
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(0f, 15f, 5f, 10f),
                                10f
                            )
                        )
                    }

                    drawCircle(
                        Color.hsl(
                            (360f / maxIterations) * index,
                            0.3f,
                            0.6f
                        ),
                        center = currentPos,
                        radius = 20f
                    )

                    previousPos = currentPos
                }


                drawLine(
                    Color.White,
                    start = Offset.Zero,
                    end = leftBound
                )

                drawLine(
                    Color.White,
                    start = Offset.Zero,
                    end = rightBound
                )
            }
        }
    }
}

private fun getXCoord(angle: Float, index: Int): Float {
    return cos(angle) * 100f * index
}

private fun getYCoord(angle: Float, index: Int): Float {
    return sin(angle) * 150f * index
}