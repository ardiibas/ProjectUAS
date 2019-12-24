package app.example.projectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.example.projectuas.room.BarangRepository
import app.example.projectuas.room.BarangRoomDatabase
import app.example.projectuas.model.Barang
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */
class BarangViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: BarangRepository
    // LiveData gives us updated words when they change.
    val allBarangs: LiveData<List<Barang>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val barangsDao = BarangRoomDatabase.getDatabase(application, viewModelScope).barangDao()
        repository = BarangRepository(barangsDao)
        allBarangs = repository.allBarangs
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on the mainthread, blocking
     * the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called viewModelScope which we
     * can use here.
     */
    fun insert(barang: Barang) = viewModelScope.launch {
        repository.insert(barang)
    }
}