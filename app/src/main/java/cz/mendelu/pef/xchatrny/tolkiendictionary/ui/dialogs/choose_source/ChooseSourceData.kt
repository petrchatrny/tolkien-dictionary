package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.dialogs.choose_source

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectFieldItem

class ChooseSourceData {
    var selectedExistingSource: SelectFieldItem<Source>? = (null)
    var newSource = Source(name = "", url = "")
    var selectableSources: List<SelectFieldItem<Source>> = listOf()
}