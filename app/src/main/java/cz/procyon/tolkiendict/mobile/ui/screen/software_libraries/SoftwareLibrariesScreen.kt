package cz.procyon.tolkiendict.mobile.ui.screen.software_libraries

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.navigation.INavigationRouter
import cz.procyon.tolkiendict.mobile.ui.component.BackArrowScreen
import cz.procyon.tolkiendict.mobile.ui.component.lists.SoftwareLibraryLT
import org.koin.androidx.compose.getViewModel

@Composable
fun SoftwareLibrariesScreen(
    navigation: INavigationRouter,
    viewModel: SoftwareLibrariesViewModel = getViewModel()
) {
    BackArrowScreen(
        drawFullScreenContent = true,
        appBarTitle = stringResource(R.string.used_software_libraries),
        onBackClick = { navigation.navigateBack() }
    ) {
        SoftwareLibrariesScreenContent(paddingValues = it, libraries = viewModel.libraries)
    }
}

@Composable
fun SoftwareLibrariesScreenContent(
    paddingValues: PaddingValues,
    libraries: List<SoftwareLibrariesViewModel.SoftwareLibrary>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        items(count = libraries.size, key = { libraries[it].packageName }) { index ->
            val lib = libraries[index]
            SoftwareLibraryLT(library = lib)
            Divider(color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}