package cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R

enum class FontSize(val labelId: Int, val size: TextUnit) {
    SMALL(R.string.small, 16.sp),
    MIDDLE(R.string.middle, 22.sp),
    BIG(R.string.big, 24.sp)
}