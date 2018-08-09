package com.example.tommacbookpro.myproject.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tommacbookpro.myproject.R


class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
