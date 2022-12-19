package com.example.crudoperation_mvvm_architecture.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crudoperation_mvvm_architecture.R
import com.example.crudoperation_mvvm_architecture.User
import com.example.crudoperation_mvvm_architecture.UserViewModel
import com.example.crudoperation_mvvm_architecture.databinding.FragmentAddBinding

class addFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAddUser.setOnClickListener {
            InsertDataToDatabase()
        }
        return binding.root
    }

    private fun InsertDataToDatabase() {
        val firstName = binding.firstName.text.toString()
        val lastname = binding.lastName.text.toString()
        val age = binding.age.text

        if (InputCheack(firstName, lastname, age)) {
            val user = User(0,firstName, lastname, Integer.parseInt(age.toString()))
            viewModel.addUser(user)

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Plz fill All fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun InputCheack(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(age))
    }

}