package com.demo.testtheeditor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import com.chinalwb.are.AREditText
import com.chinalwb.are.strategies.VideoStrategy
import com.chinalwb.are.styles.toolbar.ARE_ToolbarDefault
import com.chinalwb.are.styles.toolbar.IARE_Toolbar
import com.chinalwb.are.styles.toolitems.*
import com.demo.testtheeditor.HtmlView.Companion.HTML_TEXT
import kotlinx.android.synthetic.main.activity_editor.*

class Editor : AppCompatActivity() {


    private var mToolbar: IARE_Toolbar? = null

    private var mEditText: AREditText? = null

    private var scrollerAtEnd: Boolean = false

    private val imageStrategy = DemoImageStrategy()
    //9473191743
    private val mVideoStrategy = object : VideoStrategy {
        override fun uploadVideo(uri: Uri): String {
            try {
                Thread.sleep(3000) // Do upload here
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "https://player.vimeo.com/external/330203300.sd.mp4?s=23b97c7d9405c0bdf3ff7a27d6dca053d981d349&profile_id=164&oauth2_token_id=57447761"
        }

        override fun uploadVideo(videoPath: String): String {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "https://player.vimeo.com/external/330203300.sd.mp4?s=23b97c7d9405c0bdf3ff7a27d6dca053d981d349&profile_id=164&oauth2_token_id=57447761"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        initToolbar()
    }

    private fun initToolbar() {
        val bold = ARE_ToolItem_Bold()
        val italic = ARE_ToolItem_Italic()
        val underline = ARE_ToolItem_Underline()
        val strikethrough = ARE_ToolItem_Strikethrough()
        val fontSize = ARE_ToolItem_FontSize()
        val quote = ARE_ToolItem_Quote()
        val listNumber = ARE_ToolItem_ListNumber()
        val listBullet = ARE_ToolItem_ListBullet()
        val hr = ARE_ToolItem_Hr()
        val link = ARE_ToolItem_Link()
        val subscript = ARE_ToolItem_Subscript()
        val superscript = ARE_ToolItem_Superscript()
        val left = ARE_ToolItem_AlignmentLeft()
        val center = ARE_ToolItem_AlignmentCenter()
        val right = ARE_ToolItem_AlignmentRight()
        val image = ARE_ToolItem_Image()
//        val video = ARE_ToolItem_Video()
        val at = ARE_ToolItem_At()

        areToolbar.addToolbarItem(bold)
        areToolbar.addToolbarItem(italic)
        areToolbar.addToolbarItem(underline)
        areToolbar.addToolbarItem(strikethrough)
        areToolbar.addToolbarItem(fontSize)
        areToolbar.addToolbarItem(quote)
        areToolbar.addToolbarItem(listNumber)
        areToolbar.addToolbarItem(listBullet)
        areToolbar.addToolbarItem(hr)
        areToolbar.addToolbarItem(link)
        areToolbar.addToolbarItem(subscript)
        areToolbar.addToolbarItem(superscript)
        areToolbar.addToolbarItem(left)
        areToolbar.addToolbarItem(center)
        areToolbar.addToolbarItem(right)
        areToolbar.addToolbarItem(image)
//        areToolbar.addToolbarItem(video)
        areToolbar.addToolbarItem(at)

        
        arEditText.setToolbar(areToolbar)
        arEditText.setImageStrategy(imageStrategy)
        arEditText.setVideoStrategy(mVideoStrategy)


        setHtml()

        initToolbarArrow()
    }

    private fun setHtml() {
        // Default Html for the Editor Goes here
        val html = ""
        arEditText.fromHtml(html)
    }

    private fun initToolbarArrow() {
        val imageView = arrow
        if (areToolbar is ARE_ToolbarDefault) {
            (areToolbar as ARE_ToolbarDefault).viewTreeObserver.addOnScrollChangedListener {
                val scrollX = (areToolbar as ARE_ToolbarDefault).scrollX
                val scrollWidth = (areToolbar as ARE_ToolbarDefault).width
                val fullWidth = (areToolbar as ARE_ToolbarDefault).getChildAt(0).width

                if (scrollX + scrollWidth < fullWidth) {
                    imageView.setImageResource(R.drawable.arrow_right)
                    scrollerAtEnd = false
                } else {
                    imageView.setImageResource(R.drawable.arrow_left)
                    scrollerAtEnd = true
                }
            }
        }

        imageView.setOnClickListener(View.OnClickListener {
            if (scrollerAtEnd) {
                (areToolbar as ARE_ToolbarDefault).smoothScrollBy(-Integer.MAX_VALUE, 0)
                scrollerAtEnd = false
            } else {
                val hsWidth = (areToolbar as ARE_ToolbarDefault).getChildAt(0).width
                (areToolbar as ARE_ToolbarDefault).smoothScrollBy(hsWidth, 0)
                scrollerAtEnd = true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuId = item.itemId
        if (menuId == com.chinalwb.are.R.id.action_save) {
            val html = arEditText.getHtml()
            DemoUtil.saveHtml(this, html)
            return true
        }
        if (menuId == R.id.action_show_tv) {
            val html = arEditText.getHtml()
            val intent = Intent(this, HtmlView::class.java)
            intent.putExtra(HTML_TEXT, html)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        areToolbar.onActivityResult(requestCode, resultCode, data)
    }
}
