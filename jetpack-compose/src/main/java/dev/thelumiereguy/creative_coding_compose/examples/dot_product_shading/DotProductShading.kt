package dev.thelumiereguy.creative_coding_compose.examples.dot_product_shading

import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.romainguy.kotlin.math.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val circleRadius = 400

@OptIn(ExperimentalGraphicsApi::class)
@ExperimentalComposeUiApi
@Composable
fun DotProductShading(modifier: Modifier) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        CoroutineScope(coroutineContext).launch {
            Toast.makeText(context, "Touch somewhere on the screen", Toast.LENGTH_LONG).show()
            delay(4000)
            Toast.makeText(
                context,
                "Drag slider to change the Z position of Light source",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    BoxWithConstraints(modifier = Modifier) {

        var lightSourceZ by remember {
            mutableStateOf(600f)
        }

        val halfWidth = remember {
            constraints.maxWidth / 2
        }

        val halfHeight = remember {
            constraints.maxHeight / 2
        }


        var lightSource by remember {
            mutableStateOf(
                Float3(
                    -circleRadius.toFloat(),
                    circleRadius.toFloat(),
                    lightSourceZ
                )
            )
        }

        Surface(
            modifier = modifier
                .pointerInteropFilter { event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            lightSource = Float3(
                                event.x - halfWidth,
                                event.y - halfHeight,
                                lightSourceZ
                            )
                        }
                        MotionEvent.ACTION_MOVE -> {
                            lightSource = Float3(
                                event.x - halfWidth,
                                event.y - halfHeight,
                                lightSourceZ
                            )
                        }
                    }
                    true
                },
            color = MaterialTheme.colors.background
        ) {


            val pointsList = remember {
                (-halfWidth..halfWidth step 5).flatMap { x ->
                    (-halfHeight..halfHeight step 5).map { y ->
                        val pos = Float2(x.toFloat(), y.toFloat())
                        val z = -circleRadius + length(pos)
                        Float3(pos, z)
                    }
                }
            }

            Canvas(modifier = modifier.background(Color.Black)) {

                // Move origin to center
                translate(
                    left = halfWidth.toFloat(),
                    top = halfHeight.toFloat()
                ) {

                    pointsList.filter {
                        length(it) < circleRadius
                    }.forEach { coord ->

                        val normalizedCoord = normalize(coord)
                        val normalizedLightSource = normalize(lightSource)

                        val lightDistanceFactor = (400 / length(lightSource))

                        var dot = minOf(
                            maxOf(
                                dot(
                                    normalizedCoord,
                                    normalizedLightSource,
                                ) * lightDistanceFactor,
                                0f
                            ),
                            1f
                        )

                        if (dot.isNaN()) {
                            dot = 1f
                        }

                        drawCircle(
                            Color.hsl(
                                20f,
                                0.9f,
                                dot
                            ),
                            radius = 10f,
                            Offset(coord.x, coord.y)
                        )
                    }
                }
            }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = "-1000f", color = Color.White)
                Text(text = "Z = $lightSourceZ", color = Color.White)
                Text(text = "1000f", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Slider(
                valueRange = -1000f..1000f,
                onValueChange = {
                    lightSourceZ = it
                    lightSource = lightSource.copy(z = it)
                },
                value = lightSourceZ,
            )
        }

    }
}