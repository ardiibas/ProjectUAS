package app.example.projectuas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import app.example.projectuas.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_add.setOnClickListener {
            //            val showDetailActivityIntent = Intent(this, AddBarang::class.java)
            val showDetailActivityIntent = Intent(this, Result::class.java)
            startActivity(showDetailActivityIntent)
        }

        bt_exit.setOnClickListener {

        }
    }
}
