package com.example.crudoperation_mvvm_architecture

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crudoperation_mvvm_architecture.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUpdateBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateFname.setText(args.currentUser.FirstName)
        binding.updateLname.setText(args.currentUser.LastName)
        binding.updateAge.setText(args.currentUser.Age.toString())

        binding.btnUpdateUser.setOnClickListener {
            Updateitem(binding)
        }

        //add menu to action bar
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun Updateitem(binding: FragmentUpdateBinding) {
        val fname = binding.updateFname.text.toString()
        val lname = binding.updateLname.text.toString()
        val age = binding.updateAge.text.toString()

        if (InputCheack(fname, lname, binding.updateAge.text)) {
            val update_user = User(args.currentUser.id, fname, lname, Integer.parseInt(age))
            viewModel.updateUser(update_user)
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill All Fields", Toast.LENGTH_LONG).show()
        }
    }

    //Delete Single record
    private fun InputCheack(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->

            viewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Deleted Successfully :${args.currentUser.FirstName}",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.FirstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.FirstName}? ")
        builder.create().show()
    }
}