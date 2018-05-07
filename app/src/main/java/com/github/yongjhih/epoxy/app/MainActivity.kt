package com.github.yongjhih.epoxy.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.airbnb.epoxy.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var inputText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_sample)
        
        val recyclerView = findViewById<EpoxyRecyclerView>(R.id.recycler_view)

        recyclerView.withModels {
            pageHeader {
                id(UUID.randomUUID().toString())
                text1("Lottie Explorer")
            }
            pageHeader {
                id(UUID.randomUUID().toString())
                text1("Lottie Explorer")
            }
            inputRow {
                id(UUID.randomUUID().toString())
                text1(inputText)
            }
            inputRow {
                id(UUID.randomUUID().toString())
                text1(inputText)
            }
        }
    }
}

@EpoxyModelClass(layout = R.layout.view_holder_page_header)
abstract class PageHeaderModel(
) : EpoxyModelWithHolder<PageHeaderModel.Holder>() {
    @EpoxyAttribute var text1: String? = null

    override fun bind(holder: Holder) {
        holder.text1?.text = text1
    }

    class Holder : EpoxyHolder() {
        var text1: TextView? = null

        override fun bindView(itemView: View?) {
            text1 = itemView?.findViewById(R.id.text1)
        }
    }
}

@EpoxyModelClass(layout = R.layout.view_holder_input_row)
abstract class InputRowModel(
) : EpoxyModelWithHolder<InputRowModel.Holder>() {
    @EpoxyAttribute var text1: String? = null

    override fun bind(holder: Holder) {
        holder.edit1?.setText(text1!!)
    }

    class Holder : EpoxyHolder() {
        var edit1: EditText? = null

        override fun bindView(itemView: View?) {
            edit1 = itemView?.findViewById(R.id.edit1)
        }
    }
}

/** Easily add models to an EpoxyRecyclerView, the same way you would in a buildModels method of EpoxyController. */
fun EpoxyRecyclerView.withModels(buildModelsCallback: EpoxyController.() -> Unit) {
    setControllerAndBuildModels(object : EpoxyController() {
        override fun buildModels() {
            buildModelsCallback()
        }
    })
}

class OnTextChanged(private val callback: (CharSequence) -> Unit) : TextWatcher {

    override fun onTextChanged(
            s: CharSequence,
            start: Int,
            before: Int,
            count: Int
    ) {
        callback(s)
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
    ) {
    }
}
