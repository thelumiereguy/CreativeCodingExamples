@file:OptIn(ExperimentalGraphicsApi::class)

package dev.thelumiereguy.creative_coding_compose.examples.cubic_sphere

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import dev.thelumiereguy.creative_coding_compose.examples.stacker.Stacker
import dev.thelumiereguy.creative_coding_compose.theme.CreativeCodingComposeTheme
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

private const val repetitionCount = 12

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun CubicSphere(modifier: Modifier) {

    BoxWithConstraints(
        Modifier
            .background(Color.Black)
    ) {

        val infiniteTransition = rememberInfiniteTransition()

        val squareCount by infiniteTransition.animateFloat(
            359f,
            0f,
            infiniteRepeatable(
                tween(
                    5000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )


        val offset = (0..repetitionCount).map { index ->
            infiniteTransition.animateFloat(
                30f * (repetitionCount - index),
                30f * repetitionCount,
                infiniteRepeatable(
                    tween(
                        10000,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }

        repeat(repetitionCount) { index ->
            Canvas(
                modifier = Modifier.then(
                    modifier
                ),
            ) {
                translate(
                    constraints.maxWidth / 2f,
                    constraints.maxHeight / 2f,
                ) {

                    (0..squareCount.roundToInt() step 5).forEach { angle ->

                        val size = 5f * (index + 1)

                        rotate(
                            angle.toFloat(),
                            pivot = Offset(0f, 0f)
                        ) {
                            drawRect(
                                Color.hsl(
                                    20f,
                                    abs(index / repetitionCount.toFloat() - 0.2f),
                                    0.5f
                                ),
                                topLeft = Offset(
                                    offset[index].value,
                                    offset[index].value
                                ),
                                size = Size(size, size),
                                style = Stroke(index.toFloat()),
                                alpha = 1f / (index + 1)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RippledLinesComposablePreview() {
    CreativeCodingComposeTheme {
        Stacker(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            repetitions = 20,
            offsetProvider = { index ->
                IntOffset(
                    cos(index.toFloat()).roundToInt() * 15,
                    sin(index.toFloat()).roundToInt() * 15
                )
            }
        ) { index, modifier ->
            CubicSphere(
                modifier = modifier,
            )
        }
    }
}