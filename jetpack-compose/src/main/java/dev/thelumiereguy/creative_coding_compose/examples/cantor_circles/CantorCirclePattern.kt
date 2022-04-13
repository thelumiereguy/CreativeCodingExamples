package dev.thelumiereguy.creative_coding_compose.examples.cantor_circles

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import dev.thelumiereguy.creative_coding_compose.theme.CreativeCodingComposeTheme
import kotlin.math.abs

const val MAX_ITERATIONS = 7f

@Composable
fun CantorCirclePattern(modifier: Modifier) {

    BoxWithConstraints(modifier = Modifier) {

        val frame by rememberInfiniteTransition().animateFloat(
            initialValue = 1f,
            targetValue = 3f,
            animationSpec = infiniteRepeatable(
                tween(
                    10000,
                    easing = FastOutSlowInEasing
                ),
                RepeatMode.Reverse
            ),
        )


        val halfWidth = remember {
            constraints.maxWidth / 2
        }

        val halfHeight = remember {
            constraints.maxHeight / 2
        }


        fun DrawScope.drawCircle(
            center: Offset,
            radius: Float,
            iteration: Int = 1,
        ) {
            if (iteration > MAX_ITERATIONS) {
                return
            }

            val color = iteration / MAX_ITERATIONS

            rotate(
                degrees = (iteration % 2) * 180f * frame,
                pivot = center
            ) {

                // draw original circle
                drawCircle(
                    color = Color(
                        abs(color - 0.1f),
                        color,
                        abs(color - 0.01f),
                    ),
                    radius = radius,
                    center = center,
                    blendMode = BlendMode.Xor
                )

                val halfRadius = radius / 2f

                // draw circle inside to the left
                drawCircle(
                    center - Offset(halfRadius * frame, 0f),
                    radius = halfRadius,
                    iteration + 1
                )

                // draw circle inside to the top
                drawCircle(
                    center - Offset(0f, halfRadius * frame),
                    radius = halfRadius,
                    iteration + 1
                )


                // draw circle inside to the right
                drawCircle(
                    center + Offset(halfRadius * frame, 0f),
                    radius = halfRadius,
                    iteration + 1
                )


                // draw circle inside to the bottom
                drawCircle(
                    center + Offset(0f, halfRadius * frame),
                    radius = halfRadius,
                    iteration + 1
                )
            }
        }

        Canvas(modifier = modifier.background(Color.Black)) {

            // Move origin to center
            translate(
                left = halfWidth.toFloat(),
                top = halfHeight.toFloat()
            ) {

                drawCircle(
                    center = Offset(0f, 0f),
                    radius = minOf(halfWidth, halfHeight).toFloat(),
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CantorCirclePatternComposablePreview() {
    CreativeCodingComposeTheme {
        CantorCirclePattern(
            Modifier.fillMaxSize()
        )
    }
}