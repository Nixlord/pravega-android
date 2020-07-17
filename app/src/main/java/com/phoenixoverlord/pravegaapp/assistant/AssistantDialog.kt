package com.phoenixoverlord.pravegaapp.assistant

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.phoenixoverlord.pravega.framework.BaseActivity
import com.phoenixoverlord.pravega.toast
import com.phoenixoverlord.pravegaapp.Action
import com.phoenixoverlord.pravegaapp.DataStore
import com.phoenixoverlord.pravegaapp.R
import kotlinx.android.synthetic.main.dialog_assistant.*
import kotlinx.android.synthetic.main.dialog_assistant.actionAskForHelp
import kotlinx.android.synthetic.main.dialog_assistant.view.*

class AssistantDialog  : DialogFragment() {
    fun attachListeners(view: View?, dialog: Dialog) {
        view?.apply {
            actionAskForHelp.setOnClickListener {
                DataStore.Command.postValue(Action.CC)
                dialog.dismiss()
            }
            actionPlayGame.setOnClickListener {
                DataStore.Command.postValue(Action.GAME)
                dialog.dismiss()
            }
            actionTalkWithEva.setOnClickListener {
                DataStore.Command.postValue(Action.EVA)
                (activity as BaseActivity).toast("Listening now")
            }
        }
    }

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_assistant, null)
        val alertDialog = MaterialAlertDialogBuilder(requireContext(), R.style.CutShapeTheme)
            .setView(view)
            .create()
        attachListeners(view, alertDialog)
        return alertDialog
    }
}