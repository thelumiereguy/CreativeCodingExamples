package examples

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import k5

fun testK5() = k5 {

    show() {
        it.drawCircle(
            Color.Yellow, radius = dimensFloat.width / 2, center = Offset(dimensFloat.width / 2, dimensFloat.height / 2)
        )
    }
}