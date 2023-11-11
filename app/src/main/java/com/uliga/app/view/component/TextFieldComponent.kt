package com.uliga.app.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Black
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard

/**
 * DropDown TextField
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    modifier: Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    textFieldValue: String,
    onDropDownMenuDismissRequest: () -> Unit,
    onDropDownMenuClick: (String) -> Unit,
    dropDownMenuList: List<String>
) {
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            onExpandedChange(it)
        }
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            textStyle = UligaTheme.typography.body12,
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                unfocusedContainerColor = White,
                focusedContainerColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Grey500,
                unfocusedTextColor = Grey500
            ),
            singleLine = true,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Grey400,
                    shape = UligaTheme.shapes.large
                )
                .fillMaxWidth()
                .menuAnchor()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDropDownMenuDismissRequest,
            modifier = Modifier
                .background(White)
                .exposedDropdownSize(),
        ) {

            dropDownMenuList.forEach { accountBookCategory ->
                DropdownMenuItem(text = {
                    Text(
                        color = Grey500,
                        style = UligaTheme.typography.body12,
                        text = accountBookCategory
                    )
                }, onClick = {
                    onDropDownMenuClick(accountBookCategory)
                })
            }

        }
    }
}

/**
 * Basic TextField
 */

@Composable
fun BasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    hint: String = "",
) {
    androidx.compose.material.TextField(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Grey400,
                shape = UligaTheme.shapes.large
            )
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = UligaTheme.typography.body12,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Grey500,
            cursorColor = Grey500
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        placeholder = {
            Text(
                text = hint,
                color = Grey500,
                style = UligaTheme.typography.body12,
                maxLines = 1
            )
        }
    )
}