package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.dialogs.choose_source

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source


sealed class ChooseSourceUIState {
    object Default : ChooseSourceUIState()
    object DataChanged : ChooseSourceUIState()
    class SourceSaved(val newSource: Source) : ChooseSourceUIState()
}