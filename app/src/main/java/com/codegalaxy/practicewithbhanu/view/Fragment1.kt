package com.codegalaxy.practicewithbhanu.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.codegalaxy.practicewithbhanu.R
import com.codegalaxy.practicewithbhanu.viewmodel.SharedViewModel


class Fragment1 : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val editText = view.findViewById<EditText>(R.id.et_input_f1)

        sharedViewModel.sharedText.observe(viewLifecycleOwner) { text ->
            if (editText.text.toString() != text) {
                editText.setText(text)
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sharedViewModel.setSharedText(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }
}
