package com.cameparkare.dashboardapp.ui.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cameparkare.dashboardapp.ui.screens.settings.SettingOptionsState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LeftOptionsPanel(options : List<SettingOptionsState>, onSelectClick: (SettingOptionsState) -> Unit){
    Column(modifier = Modifier.width(220.dp).fillMaxHeight().padding(8.dp).shadow(elevation = 4.dp)
        .background(
            color = MaterialTheme.colorScheme.surface
        ).padding(0.dp, 2.dp, 8.dp, 2.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // List of available screens
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(options) { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                            .padding(4.dp) // Add padding around each row
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { onSelectClick.invoke(option) }
                            .background(
                                color = if(option.isSelected) MaterialTheme.colorScheme.onSecondary
                                else MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp)
                    ) {
                        Icon(painter = painterResource(option.iconRes),
                            contentDescription = null, modifier = Modifier.size(40.dp))
                        Text(text = stringResource(option.nameRes), fontWeight = if(option.isSelected) FontWeight.Bold else FontWeight.Normal)
                    }
                }
            }
        }
    }
}