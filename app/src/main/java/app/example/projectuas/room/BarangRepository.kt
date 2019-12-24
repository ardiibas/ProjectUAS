package app.example.projectuas.room

import androidx.lifecycle.LiveData
import app.example.projectuas.model.Barang

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class BarangRepository(private val barangDao: BarangDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allBarangs: LiveData<List<Barang>> = barangDao.getAllBarang()

    suspend fun insert(barang: Barang) {
        barangDao.insert(barang)
    }
}