package com.demo.testtheeditor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        htmlView.setOnClickListener {
            val intent = Intent(this,HtmlView::class.java)
            startActivity(intent)
        }

        htmlEditor.setOnClickListener {
            val intent = Intent(this,Editor::class.java)
            startActivity(intent)
        }
    }
}
