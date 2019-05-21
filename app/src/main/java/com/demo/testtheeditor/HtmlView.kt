package com.demo.testtheeditor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_html_view.*

class HtmlView : AppCompatActivity() {
    companion object {
        val HTML_TEXT = "html_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_view)

        var s: String? = intent.getStringExtra(HTML_TEXT)
        if (s == null ) {
            s = "<html><body><p><b>aaaa</b></p><p><i>bbbb</i></p>\n" +
                    "    <p><u>cccc</u></p>\n" +
                    "    <p><span style=\"text-decoration:line-through;\">dddd</span></p>\n" +
                    "    <p style=\"text-align:start;\">Alignleft</p>\n" +
                    "    <p style=\"text-align:center;\">Align center</p>\n" +
                    "    <p style=\"text-align:end;\">Align right</p>\n" +
                    "    <p style=\"text-align:start;\">Align left</p>\n" +
                    "    <p style=\"text-align:start;\">Hello left<span style=\"background-color:#FFFF00;\"> good?</span> yes</p>\n" +
                    "    <p style=\"text-align:start;\">Text color <span style=\"color:#FF5722;\">red </span><span style=\"color:#4CAF50;\">green </span><span style=\"color:#2196F3;\">blue </span><span style=\"color:#9C27B0;\">purple</span><span style=\"color:#000000;\"> normal black</span></p>\n" +
                    "    <br>\n" +
                    "    </body></html>"
        }
        areTextView.fromHtml(s)


    }
}
