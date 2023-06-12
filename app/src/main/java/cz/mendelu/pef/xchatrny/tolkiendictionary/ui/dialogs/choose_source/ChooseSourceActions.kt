package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.dialogs.choose_source

interface ChooseSourceActions {
    fun onDataChange(data: ChooseSourceData)
    fun saveNewSource()
}