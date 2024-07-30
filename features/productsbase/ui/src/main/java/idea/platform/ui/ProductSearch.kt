package idea.platform.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import idea.platform.testtask.model.SearchData
import idea.platform.uikit.TestTaskTheme

@Composable
internal fun ProductSearch(
    modifier: Modifier,
    onSearchDataChange: (SearchData) -> Unit
) {
    var data: SearchData by remember {
        mutableStateOf(SearchData.EMPTY_PARAMETERS)
    }

    OutlinedTextField(
        value = data.name,

        label = {
            Text(
                text = stringResource(id = R.string.products_search),
                fontWeight = FontWeight.Light
            )
        },

        onValueChange = {
            data = data.copy(it)

            onSearchDataChange(data)
        },

        maxLines = 1,

        leadingIcon = {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Search),
                tint = Color.Black,
                contentDescription = null
            )
        },

        trailingIcon = {
            if (data.isErasable()) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Default.Clear),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = modifier
                        .clickable {
                            data = SearchData.EMPTY_PARAMETERS

                            onSearchDataChange(data)
                        }
                        .padding(4.dp)
                )
            }
        },

        colors = TextFieldDefaults.colors(
            focusedLabelColor = Color.Black,
            unfocusedContainerColor = TestTaskTheme.colorScheme.background,
            focusedContainerColor = TestTaskTheme.colorScheme.background,
            focusedIndicatorColor = TestTaskTheme.colorScheme.secondary,
            cursorColor = TestTaskTheme.colorScheme.secondary,
        ),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    )
}