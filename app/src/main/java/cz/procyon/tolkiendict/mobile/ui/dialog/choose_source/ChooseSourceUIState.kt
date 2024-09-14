package cz.procyon.tolkiendict.mobile.ui.dialog.choose_source

import cz.procyon.tolkiendict.mobile.model.Source


sealed class ChooseSourceUIState {
    object Default : ChooseSourceUIState()
    object DataChanged : ChooseSourceUIState()
    class SourceSaved(val newSource: Source) : ChooseSourceUIState()
}