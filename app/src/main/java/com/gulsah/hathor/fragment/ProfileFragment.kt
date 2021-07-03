package com.gulsah.hathor.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gulsah.hathor.R
import com.gulsah.hathor.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var layout: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val sharedPreferences =
            context?.getSharedPreferences("com.gulsah.hathor", Context.MODE_PRIVATE)
        val fullName = sharedPreferences?.getString("STRING_NAME", null)
        val mail = sharedPreferences?.getString("STRING_MAIL", null)
        val phoneNumber = sharedPreferences?.getString("STRING_PHONE", null)
        layout.textViewFullName.text = fullName
        layout.textViewMail.text = mail
        layout.textViewPhone.text = phoneNumber


        return layout.root
    }
}