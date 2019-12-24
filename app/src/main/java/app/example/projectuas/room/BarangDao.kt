package app.example.projectuas.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.example.projectuas.model.Barang

@Dao
interface BarangDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from barang_table ORDER BY nama ASC")
    fun getAllBarang(): LiveData<List<Barang>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(barang: Barang)

    @Query("DELETE FROM barang_table")
    suspend fun deleteAll()
}