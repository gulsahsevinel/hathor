package com.gulsah.hathor.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.gulsah.hathor.R


class CustomDialogFragment() : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val layout = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_dialog, container, false)
        val view = inflater.inflate(R.layout.fragment_custom_dialog, container, false)

        return view
    }

}

