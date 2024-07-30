package idea.platform.testtask.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import idea.platform.ui.ProductsMainScreen
import idea.platform.uikit.TestTaskTheme

class RootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestTaskTheme {
                ProductsMainScreen()
            }
        }
    }
}