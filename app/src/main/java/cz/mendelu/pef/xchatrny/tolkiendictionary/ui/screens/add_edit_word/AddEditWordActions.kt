package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

interface AddEditWordActions {
    fun onDataChange(data: AddEditWordData)
    fun saveWord(update: Boolean)
    fun deleteWord()
}