package id.ratabb.quran.ui.surah

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.ratabb.quran.ui.common.NumberArabic
import id.ratabb.quran.ui.common.TextArabic
import id.ratabb.quran.ui.theme.DiwaniBent
import items.entity.SurahEntity

@Composable
fun SurahInfoListScreen(vm: SurahInfoViewModel, setNumSurah: (Int) -> Unit) {
    val state = vm.surahData.observeAsState(emptyList())
    SurahInfoGrid(state.value) { entity -> setNumSurah(entity.number) }
}

@Composable
fun SurahInfoGrid(
    data: List<SurahEntity>,
    onSurahClick: (SurahEntity) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 12.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data) { entity ->
            Card(
                modifier = Modifier
                    .padding(4.dp).padding(bottom = 8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onSurahClick(entity) },
                elevation = 8.dp,
                border = BorderStroke(1.dp, MaterialTheme.colors.primary)
            ) {
                SurahInfoItem(entity, Modifier.fillMaxWidth().padding(8.dp))
            }
        }
    }
}

@Composable
fun SurahInfoItem(surah: SurahEntity, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth().weight(1F)
        ) {
            TextArabic(
                surah.textArabic!!,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
            Text(surah.textIndo!!)
            Divider(Modifier.height(1.dp), Color.LightGray)
            Text("${surah.revIndo}\n${surah.numberOfVerses} Ayah")
            Spacer(Modifier.height(2.dp))
        }
        Text(
            text = surah.textArabic!!,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            fontFamily = DiwaniBent,
            modifier = Modifier.wrapContentSize(unbounded = true).weight(1F)
        )
        Surface(
            modifier = Modifier.size(48.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
            color = MaterialTheme.colors.primary.copy(alpha = 0.35F)
        ) {
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                NumberArabic(
                    number = surah.number,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
