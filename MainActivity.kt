package com.buildmaxai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF071426)
private val Panel = Color(0xFF10243B)
private val Cyan = Color(0xFF35D6D0)
private val Muted = Color(0xFF9FB2C8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BuildMaxScreen() }
    }
}

@Composable
fun BuildMaxScreen() {
    var market by remember { mutableStateOf("REAL FOREX") }
    var pair by remember { mutableStateOf("EUR/USD") }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(Navy).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column {
                Text("BUILD MAX AI", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text("SMART MARKET ANALYSIS", color = Cyan, fontSize = 11.sp, letterSpacing = 2.sp)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("REAL FOREX", "OTC").forEach { item ->
                    FilterChip(
                        selected = market == item,
                        onClick = { market = item },
                        label = { Text(item) }
                    )
                }
            }

            Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(20.dp)) {
                Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("MARKET PAIR", color = Muted, fontSize = 12.sp)
                    var expanded by remember { mutableStateOf(false) }
                    Box {
                        OutlinedButton(onClick = { expanded = true }) { Text(pair, color = Color.White) }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            listOf("EUR/USD", "GBP/USD", "USD/JPY", "AUD/USD", "USD/CHF", "USD/CAD", "NZD/USD").forEach {
                                DropdownMenuItem(text = { Text(it) }, onClick = { pair = it; expanded = false })
                            }
                        }
                    }
                    HorizontalDivider(color = Color(0xFF29415B))
                    Text("CURRENT STATUS", color = Muted, fontSize = 12.sp)
                    Text("WAIT", color = Cyan, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
                    Text("NO VERIFIED SETUP", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("Live market data is not connected yet.", color = Muted, fontSize = 12.sp)
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                InfoTile("TIMEFRAME", "1 MIN")
                InfoTile("SCAN RATE", "60 SEC")
                InfoTile("SIGNALS", "FILTERED")
            }

            Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("SMARTFLOW CHECKLIST", color = Color.White, fontWeight = FontWeight.Bold)
                    listOf("EMA 9 / EMA 21 trend", "RSI 14 momentum", "MACD confirmation", "Support / resistance", "Closed candle confirmation").forEach {
                        Text("•  $it", color = Muted, fontSize = 13.sp)
                    }
                }
            }

            Spacer(Modifier.weight(1f))
            Text(
                "Educational prototype only • No trade execution • No guaranteed signals",
                color = Muted,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun InfoTile(title: String, value: String) {
    Card(
        modifier = Modifier.weight(1f),
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(title, color = Muted, fontSize = 9.sp)
            Text(value, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}
