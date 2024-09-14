package cz.procyon.tolkiendict.mobile.ui.dialog.choose_source

import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.ui.component.fields.SelectFieldItem

class ChooseSourceData {
    var selectedExistingSource: SelectFieldItem<Source>? = (null)
    var newSource = Source(name = "", url = "")
    var selectableSources: List<SelectFieldItem<Source>> = listOf()
}