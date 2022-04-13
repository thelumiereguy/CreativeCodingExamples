package dev.thelumiereguy.creative_coding_compose.examples.circle_pattern

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import dev.thelumiereguy.creative_coding_compose.theme.CreativeCodingComposeTheme
import java.lang.Math.PI
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.random.Random


@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun DrawCirclePatternComposable(
    xCoord: Float,
    yCoord: Float,
    onFinished: (Float) -> Unit
) {

    val frame = rememberInfiniteTransition().animateFloat(
        initialValue = 1f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            tween(5000, easing = CubicBezierEasing(
                0f,0f,1f,0.2f
            )),
            RepeatMode.Reverse
        ),
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val rings = maxOf(canvasWidth, canvasHeight).roundToInt() / 3

        translate(xCoord, yCoord) {
            (1..rings step 50).forEach { offset ->
                val circumference = 2 * PI * offset
                val circles = circumference / 50
                val angleSteps = (360 / floor(circles.toFloat()))
                var angle = 0f

                while (angle <= 360) {
                    val circleRadius = (
                            (kotlin.math.sin(
                                (offset * frame.value * 0.01f)
                            )) * 5f) + 4f

                    rotate(
                        angle,
                        Offset(
                            0f,
                            0f
                        )
                    ) {
                        val color = Color.hsl(
                            angle % 40,
                            Random((angle + frame.value * 0.01f).roundToInt()).nextFloat(),
                            0.5f,
                        )

                        drawCircle(
                            color,
                            circleRadius,
                            Offset(
                                offset.toFloat(),
                                0f
                            )
                        )
                    }
                    angle += angleSteps
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultCirclePatternComposablePreview() {
    CreativeCodingComposeTheme {
        DrawCirclePatternComposable(
            0f, 0f
        ) {}
    }
}