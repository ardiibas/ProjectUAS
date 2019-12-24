package app.example.projectuas.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import app.example.projectuas.R
import kotlinx.android.synthetic.main.add_barang.*
import kotlinx.android.synthetic.main.toolbar_white.*

class AddBarang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_barang)

        initToolbar();

        bt_Next.setOnClickListener {
            val gotoResult = Intent()
            if (TextUtils.isEmpty(jT_nama.text) ||
                    TextUtils.isEmpty(jT_merk.text) ||
                    TextUtils.isEmpty(jT_Qty.text) ||
                    TextUtils.isEmpty(jTHarga.text)) {
                setResult(Activity.RESULT_CANCELED, gotoResult)
            } else {
                val nama = jT_nama.text.toString()
                val merk = jT_merk.text.toString()
                val qty = jT_Qty.text.toString()
                val harga = jTHarga.text.toString()

                gotoResult.putExtra(EXTRA_REPLY_NAME, nama)
                gotoResult.putExtra(EXTRA_REPLY_MERK, merk)
                gotoResult.putExtra(EXTRA_REPLY_QTY, qty.toInt())
                gotoResult.putExtra(EXTRA_REPLY_HARGA, harga.toInt())
                setResult(Activity.RESULT_OK, gotoResult)
            }
            finish()
        }
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_house);
        toolbar.setNavigationOnClickListener {
            val backtoHome = Intent(this, MainActivity::class.java)
            startActivity(backtoHome)
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_NAME = "com.example.android.wordlistsql.REPLY_NAME"
        const val EXTRA_REPLY_MERK = "com.example.android.wordlistsql.REPLY_MERK"
        const val EXTRA_REPLY_QTY = "com.example.android.wordlistsql.REPLY_QTY"
        const val EXTRA_REPLY_HARGA = "com.example.android.wordlistsql.REPLY_HARGA"
    }
}