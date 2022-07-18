package dev.thelumiereguy.creative_coding_compose.examples.animated_shapes

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import dev.romainguy.kotlin.math.radians
import dev.thelumiereguy.creative_coding_compose.theme.CreativeCodingComposeTheme
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun AnimatedShapes(modifier: Modifier) {
    BoxWithConstraints(
        Modifier
            .background(Color.Black)
    ) {


        val sideCount by rememberInfiniteTransition().animateFloat(
            1f,
            10f,
            infiniteRepeatable(
                tween(
                    3000,
                ),
                repeatMode = RepeatMode.Reverse
            )
        )

        Canvas(
            modifier = Modifier.then(
                modifier
            ),
        ) {

            translate(
                constraints.maxWidth / 2f,
                constraints.maxHeight / 2f,
            ) {

                repeat(sideCount.roundToInt()) {

                    rotate(
                        (360 / sideCount) * it,
                        pivot = Offset.Zero
                    ) {

                        drawLine(
                            Color.White,
                            start = Offset(
                                cos(0f) * 150f,
                                sin(0f) * 150f
                            ),
                            end = Offset(
                                cos(radians((360 / sideCount))) * 150f,
                                sin(radians((360 / sideCount))) * 150f
                            )
                        )

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedShapesPreview() {
    CreativeCodingComposeTheme {
        AnimatedShapes(
            Modifier.fillMaxSize()
        )
    }
}