package com.cameparkare.dashboardapp.ui.screens.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.components.AppButton
import com.cameparkare.dashboardapp.ui.theme.*
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingTopBar(onBackClick: () -> Unit, onSaveClick: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth().background(HeaderColor).padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            enabled = true
        ) {
            Image(painter = painterResource(Res.drawable.ic_back_arrow),
                contentDescription = stringResource(Res.string.back_button_alt))
        }

        Text(text = stringResource(Res.string.app_name), color = WhiteColor)


        // Save button
        AppButton(text = stringResource(Res.string.save_button), onClick = onSaveClick,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = CameBlueColor,
                contentColor = WhiteColor
            )
        )
    }
}