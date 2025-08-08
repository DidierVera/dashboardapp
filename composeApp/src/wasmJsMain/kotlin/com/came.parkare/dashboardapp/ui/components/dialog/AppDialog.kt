@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.config.constants.Constants
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.CloseButton
import com.came.parkare.dashboardapp.ui.theme.BlackColor
import com.came.parkare.dashboardapp.ui.theme.LightBlackColor
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.accept_button
import dashboardapp.composeapp.generated.resources.cancel_button
import dashboardapp.composeapp.generated.resources.password_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun AppDialog(modifier: Modifier = Modifier){
    val viewModel: AppDialogViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()

    if (showDialog){
        ShowDialog(modifier, state)
    }

}

@Composable
fun ShowDialog(modifier: Modifier = Modifier, model: AppDialogState) {
    val viewModel: AppDialogViewModel = koinViewModel()
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = modifier.fillMaxSize().background(LightBlackColor.copy(alpha = 0.8f))
                .clickable(enabled = false){}
        )
        Column(modifier = Modifier.floatingButton().width(280.dp).heightIn(min = 80.dp).padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {

            CloseButton(modifier = Modifier.size(35.dp).align(Alignment.End)){
                viewModel.hideDialog()
            }

            DialogMessage(modifier = Modifier.align(Alignment.CenterHorizontally),
                message = model.message)

            PasswordField(model.requirePassword)

            ActionButtons(modifier = Modifier.align(Alignment.CenterHorizontally),
                model.onAccept, model.onCancel)
        }
    }
}

@Composable
fun ActionButtons(modifier: Modifier = Modifier,
                  onAccept: () -> Unit, onCancel: () -> Unit) {
    val viewModel: AppDialogViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {

        AppButton(text = stringResource(Res.string.accept_button), onClick = {
            onAccept.invoke()
            viewModel.hideDialog()
        }, isEnabled = state.isAcceptButtonActive)
        AppButton(text = stringResource(Res.string.cancel_button), buttonColors = ButtonDefaults.buttonColors().copy(
            containerColor = Color.DarkGray,
            contentColor = Color.White,
            ),
            onClick = {
                onCancel.invoke()
                viewModel.hideDialog()
            }
        )
    }
}

@Composable
private fun PasswordField(requirePassword: Boolean) {
    val viewModel: AppDialogViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val passwordState by viewModel.passwordState.collectAsState()
    if (requirePassword){
        TextField(value = passwordState, onValueChange = {
            viewModel.setPassword(it)
        }, label = {
            Text(text = stringResource(Res.string.password_label),
                style = MaterialTheme.typography.titleSmall)
        }, visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    if(viewModel.state.value.isAcceptButtonActive){
                        state.onAccept.invoke()
                        viewModel.hideDialog()
                    }
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
}

@Composable
private fun DialogMessage(modifier: Modifier = Modifier, message: String){
    Text(message, color = BlackColor,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
        modifier = modifier.widthIn(max = 250.dp))
}
