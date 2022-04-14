package examples

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import k5
import org.jetbrains.skia.RuntimeEffect

val skiaShaderCode = """
    float color(vec2 ch, vec2 a, vec2 b, float bb, float cb){
        return 9.0 / (a.x * a.x + a.y * a.y)  +
        bb / sqrt(b.x * b.x + b.y * b.y);
    }

    vec4 main(vec2 fragcoord) { 
        vec2 a = fragcoord - iResolution.xy * 0.5;
        float dx = iResolution.x * 0.4;
        float dy = iResolution.x * 0.3 / 16.0 * 9.0;
        
        vec2 b = a + vec2(dx * cos(iTime), dy * sin(iTime));
        
        float cl = color(fragcoord, a, b, 8.0 + 7.5 * sin(sin(iTime)), 8.0 - 7.5 * sin(sin(iTime)));
        cl *= iResolution.x / 1000.0;
        return vec4(cl * (0.5 + 0.5 * cos(iTime)), cl * 
                    (0.5 + 0.5 * sin(iTime)), cl * (0.5 - 0.25 * sin(iTime)), 1.0);
    }

""".trimIndent()

val compositeRuntimeEffect = RuntimeEffect.makeForShader(skiaShaderCode)

fun testK5() = k5 {
    show {
        it.drawCircle(
            Color.Yellow,
            radius = dimensFloat.width / 2,
            center = Offset(dimensFloat.width / 2, dimensFloat.height / 2)
        )
    }
}