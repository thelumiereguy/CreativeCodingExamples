package dev.thelumiereguy.creative_coding_compose.examples.rippled_lines

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import dev.romainguy.kotlin.math.Float2
import dev.romainguy.kotlin.math.length
import dev.thelumiereguy.creative_coding_compose.theme.CreativeCodingComposeTheme

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun RippledLines(modifier: Modifier) {

    BoxWithConstraints(modifier = Modifier) {

        val frame by rememberInfiniteTransition().animateFloat(
            initialValue = 1f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                tween(
                    5000,
                    easing = LinearEasing
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

        Canvas(modifier = modifier.background(Color.Black)) {

            // Move origin to center
            translate(
                halfWidth.toFloat(),
                halfHeight.toFloat()
            ) {

                (-halfWidth..halfWidth step 50).forEach { x ->

                    (-halfHeight..halfHeight step 30).forEach { y ->

                        val coord = Float2(x.toFloat(), y.toFloat())

                        // Rotate by length of vector, pivot at line start
                        rotate(
                            degrees = frame + length(coord),
                            pivot = Offset(coord.x, coord.y)
                        ) {
                            drawLine(
                                Color.White,
                                start = Offset(coord.x, coord.y),
                                end = Offset(coord.x + 30, coord.y),
                                strokeWidth = 1.5f
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
        RippledLines(
            Modifier.fillMaxSize()
        )
    }
}