package com.example.crudoperation_mvvm_architecture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crudoperation_mvvm_architecture.databinding.CustomListBinding
import com.example.crudoperation_mvvm_architecture.ListFragmentDirections

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =  CustomListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        holder.txtId.text = currentItem.id.toString()
        holder.txtFname.text = currentItem.FirstName
        holder.txtLname.text = currentItem.LastName
        holder.txtage.text = currentItem.Age.toString()
        holder.customlist.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    class MyViewHolder(val binding: CustomListBinding) :RecyclerView.ViewHolder(binding.root){
       val customlist = binding.customList
       val txtId = binding.idTxt
       val txtFname = binding.firstNameTxt
       val txtLname = binding.lastNameTxt
       val txtage = binding.ageTxt
    }

    fun setData(user : List<User>)
    {
        this.userList = user
        notifyDataSetChanged()
    }
}