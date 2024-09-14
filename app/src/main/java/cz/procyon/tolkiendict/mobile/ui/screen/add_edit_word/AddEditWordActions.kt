package cz.procyon.tolkiendict.mobile.ui.screen.add_edit_word

interface AddEditWordActions {
    fun onDataChange(data: cz.procyon.tolkiendict.mobile.ui.screen.add_edit_word.AddEditWordData)
    fun saveWord(update: Boolean)
    fun deleteWord()
}