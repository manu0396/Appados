package com.example.appadoskotlin2.ui.diologs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.appadoskotlin2.ui.publish.PublishFragment

private const val TITLE = "Confirmar"
private const val DESCRIPTION = "description_param"

class PublishDiolog: DialogFragment() {
    internal lateinit var listener: ConfirmationDialogListener
    private var title: String? = null
    private var description: String? = null
    private lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            listener = targetFragment as ConfirmationDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((targetFragment.toString() + " must implement ConfirmationDialogListener"))
        }

        arguments?.let {
            title = it.getString(TITLE)
            description = it.getString(DESCRIPTION)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            title?.let { title -> builder.setTitle(title) }
            input = EditText(this.context)
            input.setHint("Descripción")
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder
                .setMessage(description)
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { _, _ ->
                        var d_Text = input.text.toString()
                        savedInstanceState?.putString("user_des", d_Text)
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { _, _ ->
                        listener.onDialogNegativeClick(this)
                    })
                .setView(input)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface ConfirmationDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String?, description: String) =
            PublishDiolog().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putString(DESCRIPTION, description)
                }
            }
    }
}