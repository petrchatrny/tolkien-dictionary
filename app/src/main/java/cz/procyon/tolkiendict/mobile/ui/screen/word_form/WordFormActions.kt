package cz.procyon.tolkiendict.mobile.ui.screen.word_form

interface WordFormActions {
    fun onDataChange(data: WordFormData)
    fun saveWord(update: Boolean)
    fun deleteWord()
}