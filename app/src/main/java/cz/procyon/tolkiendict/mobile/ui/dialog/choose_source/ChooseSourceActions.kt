package cz.procyon.tolkiendict.mobile.ui.dialog.choose_source

interface ChooseSourceActions {
    fun onDataChange(data: ChooseSourceData)
    fun saveNewSource()
}