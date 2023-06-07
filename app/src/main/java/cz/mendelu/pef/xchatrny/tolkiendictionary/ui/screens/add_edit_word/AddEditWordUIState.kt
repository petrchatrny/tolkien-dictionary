package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

sealed class AddEditWordUIState {
    object Loading: AddEditWordUIState()
    object Default: AddEditWordUIState()
    object Saving: AddEditWordUIState()

    object DataChanged: AddEditWordUIState()
    object ValidationError: AddEditWordUIState()

    object WordCreated: AddEditWordUIState()
    object WordUpdated: AddEditWordUIState()
    object WordDeleted: AddEditWordUIState()
}