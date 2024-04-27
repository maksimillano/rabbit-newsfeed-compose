package preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.tooling.preview.Preview

data class SampleNode(
    var number: Int
) : Modifier.Node() {
    override fun onAttach() {
        println()
    }

    override fun onDetach() {
        println()
    }
}
class SampleElement(val number: Int) : ModifierNodeElement<SampleNode>() {
    override fun create(): SampleNode = SampleNode(0)
    override fun equals(other: Any?): Boolean = number == other
    override fun hashCode(): Int = number.hashCode()
    override fun update(node: SampleNode) {
        node.number = number
    }
}

@Stable
fun Modifier.sample(number: Int): Modifier {
    return this then SampleElement(number)
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .sample(12)
    ) {

    }
}

@Composable
@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420")
fun HeaderPreview() {
    Header()
}