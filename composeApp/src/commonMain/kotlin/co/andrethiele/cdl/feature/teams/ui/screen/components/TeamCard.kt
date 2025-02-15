// Copyright 2025 - André Thiele

package co.andrethiele.cdl.feature.teams.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cdl.composeapp.generated.resources.Res
import co.andrethiele.cdl.feature.teams.ui.model.TeamUiModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TeamCard(model: TeamUiModel, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
  val logoBitmap by
    produceState<ImageBitmap?>(null) { value = Res.readBytes(model.logoPath).decodeToImageBitmap() }

  Box(modifier = modifier) {
    Box(
      modifier =
        Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(16.dp)).clickable {
          onClick()
        }
    ) {
      Box(
        modifier =
          Modifier.fillMaxSize()
            .background(
              brush =
                Brush.verticalGradient(
                  colors = listOf(Color.Transparent, (model.tint).copy(alpha = .5f)),
                  startY = 0f,
                  endY = Float.POSITIVE_INFINITY,
                )
            )
      )

      logoBitmap?.let {
        Image(
          bitmap = it,
          contentDescription = null,
          modifier = Modifier.fillMaxSize().padding(32.dp),
          contentScale = ContentScale.Fit,
        )
      }

      Text(
        text = model.name,
        modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
      )
    }
  }
}
