package app.example.projectuas.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.example.projectuas.R
import app.example.projectuas.adapter.BarangListAdapter
import app.example.projectuas.model.Barang
import app.example.projectuas.viewmodel.BarangViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Result : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var barangViewModel: BarangViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BarangListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        barangViewModel = ViewModelProvider(this).get(BarangViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        barangViewModel.allBarangs.observe(this, Observer { barangs ->
            // Update the cached copy of the words in the adapter.
            barangs?.let { adapter.setBarangs(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@Result, AddBarang::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(AddBarang.EXTRA_REPLY_NAME)?.let {
                val barang = Barang(
                        it,
                        intentData.getStringExtra(AddBarang.EXTRA_REPLY_MERK),
                        intentData.getIntExtra(AddBarang.EXTRA_REPLY_QTY, 0),
                        intentData.getIntExtra(AddBarang.EXTRA_REPLY_HARGA, 0))
                barangViewModel.insert(barang)
                Unit
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }

}
