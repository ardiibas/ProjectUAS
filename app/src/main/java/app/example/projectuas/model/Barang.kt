package app.example.projectuas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang_table")
data class Barang(
    @PrimaryKey @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "merek") val merek: String,
    @ColumnInfo(name = "qty") val qty: Int,
    @ColumnInfo(name = "harga") val harga: Int
)